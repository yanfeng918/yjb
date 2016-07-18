package com.yongjinbao.houseProperty.service.impl;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.enums.expense.EXPENSES_TYPE;
import com.yongjinbao.enums.house.HouseTypeEnum;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.finance.entity.ExtraAward;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.entity.Income.INCOME_TYPE;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.finance.service.IExpensesService;
import com.yongjinbao.finance.service.IIncomeService;
import com.yongjinbao.finance.service.IWithDrawService;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.houseProperty.dao.IHouseInfoPropertyDao;
import com.yongjinbao.houseProperty.dto.*;
import com.yongjinbao.houseProperty.entity.HouseInfoProperty;
import com.yongjinbao.houseProperty.entity.State;
import com.yongjinbao.houseProperty.service.IHouseInfoPropertyService;
import com.yongjinbao.houseProperty.vo.*;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IBrowseFavoriteHouseInfoService;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;
import com.yongjinbao.utils.CommonUtils;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;
import com.yongjinbao.utils.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yanfeng on 2015/8/18.
 */
@Service
public class HouseInfoPropertyService extends BaseServiceImpl<HouseInfoProperty,Integer>
		implements IHouseInfoPropertyService {
	
    @Inject
    private IHouseInfoPropertyDao houseInfoDao;
   
    @Inject
	private IAreaService areaService;
    
    @Inject
    private IBrowseFavoriteHouseInfoService brosweHouseInfoService;
    
    @Inject
    private IIncomeService incomeService;
    
    @Inject
    private IExpensesService expensesService;
	
    @Inject 
    private IMemberService memberService;
    
    @Inject
    private IWithDrawService withDrawService;
    
    @Inject
    public void setBaseDao(IHouseInfoPropertyDao houseInfoDao) {
        super.setBaseDao(houseInfoDao);
    }
    
    /**
     * 数据列表缓存房源数据
     * @param area_id 当前查询的区域id
     * @param pageNumber 当前查询的页码
     * @param isList 缓存的是否为LIST
     * @param isLevel3 是否最后的版块区域，即第三级区域
     * @return
     */
    private String initRedisKeyForHouseInfoList(long area_id, int pageNumber, boolean isList, boolean isLevel3){
    	String keyList = isLevel3?"HouseInfoCdtListProperty":"HouseInfoLikeListProperty";
    	String keyCount = isLevel3?"HouseInfoCdtListCountProperty":"HouseInfoLikeListCountProperty";
    	return (isList?keyList:keyCount)+"-"+area_id+"-"+pageNumber;
    }
    
    /**
     * 是否需要缓存
     * @param pageNumber 当前查询的页码
     * @param isLevel3 是否最后的版块区域，即第三级区域
     * @return
     */
    private boolean isNeedToRedis(int pageNumber, boolean isLevel3){
    	int maxPage = isLevel3?SettingUtils.get().getRedis_level3_maxPage():
    		SettingUtils.get().getRedis_level1and2_maxPage();
    	return pageNumber<maxPage;
    }
    
    /**
     * 当前查询是否属于条件查询
     * @param getHouseInfoDto
     * @return
     */
    private boolean isCondition(GetHouseInfoDto getHouseInfoDto){
    	if (getHouseInfoDto.getSalePrice()!=null&&!getHouseInfoDto.getSalePrice().equals("")) {
			return true;
		}else if (getHouseInfoDto.getHouseShape()!=null&&!getHouseInfoDto.getHouseShape().equals("")) {
			return true;
		}else if (getHouseInfoDto.getAreaSize()!=null&&!getHouseInfoDto.getAreaSize().equals("")) {
			return true;
		}else if(!StringUtils.isEmpty(getHouseInfoDto.getCommunity())){
			return true;
		}
    	return false;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public <T> Pager<T> getHouseInfo(GetHouseInfoDto getHouseInfoDto) {
        getHouseInfoDto.setPageOffset((getHouseInfoDto.getPageNumber()-1)*getHouseInfoDto.getPageSize());
        Pager<HouseInfoPropertyAndFavouriteStatusVO> pages=new Pager<HouseInfoPropertyAndFavouriteStatusVO>();
        int total=0;
        List<HouseInfoPropertyAndFavouriteStatusVO> list = new ArrayList<HouseInfoPropertyAndFavouriteStatusVO>();
        //新增逻辑：如果地域有子节点那就模糊查询，否则直接条件查询
        int area_id = getHouseInfoDto.getArea_id();
        List<Area> findChildren = areaService.findChildren(area_id);
        //数据列表缓存过期时间为5分钟
        int expired_Time = SettingUtils.get().getRedis_expire_5Min();
        boolean isCondition = isCondition(getHouseInfoDto);
        if (isCondition) {
        	if(findChildren.isEmpty()){
        		//System.out.println("******有条件，精确查询**********");
        		list = houseInfoDao.getHouseInfoCdt(getHouseInfoDto);
            	total=houseInfoDao.getHouseInfoCdtCount(getHouseInfoDto);
        	}else {
        		//System.out.println("******有条件，模糊查询**********");
        		list = houseInfoDao.getHouseInfoLike(getHouseInfoDto);
            	total=houseInfoDao.getHouseInfoLikeCount(getHouseInfoDto);
			}
		}else {
			if(findChildren.isEmpty()){
	        	//子节点为空时，表示当前区域为板块，即第三级
	        	String listCdtKey = initRedisKeyForHouseInfoList(area_id, getHouseInfoDto.getPageNumber(),true, true);
	        	String listCdtCountKey = initRedisKeyForHouseInfoList(area_id, getHouseInfoDto.getPageNumber(),false, true);
	            byte[] listCdtBs = RedisUtils.get(SerializeUtil.serialize(listCdtKey));
	            byte[] listCdtCountBs = RedisUtils.get(SerializeUtil.serialize(listCdtCountKey));
	            
	        	if (listCdtBs == null ) {
					//没有缓存时
	        		list = houseInfoDao.getHouseInfoCdt(getHouseInfoDto);
	            	total=houseInfoDao.getHouseInfoCdtCount(getHouseInfoDto);
	            	//System.out.println("******三级区域未缓存**********");
	            	if (isNeedToRedis(getHouseInfoDto.getPageNumber(), true)&&!isCondition) {
	            		//页数小于最大缓存页数时，可进行缓存！
	            		RedisUtils.set(SerializeUtil.serialize(listCdtKey), SerializeUtil.serialize(list),expired_Time);
	            		RedisUtils.set(SerializeUtil.serialize(listCdtCountKey), SerializeUtil.serialize(total),expired_Time);
	            	}
				}else {
					//System.out.println("******三级区域有缓存**********");
					list = (List<HouseInfoPropertyAndFavouriteStatusVO> )SerializeUtil.unserialize(listCdtBs);
	            	total=(int) SerializeUtil.unserialize(listCdtCountBs);
				}
	        }else{
	        	//第一、第二级区域查询
	        	String listLikeKey = initRedisKeyForHouseInfoList(area_id, getHouseInfoDto.getPageNumber(),true, false);
	        	String listLikeCountKey = initRedisKeyForHouseInfoList(area_id, getHouseInfoDto.getPageNumber(),false, false);
	        	byte[] listLikeBs = RedisUtils.get(SerializeUtil.serialize(listLikeKey));
	            byte[] listLikeCountBs = RedisUtils.get(SerializeUtil.serialize(listLikeCountKey));
	        	if (listLikeBs==null||listLikeCountBs==null) {
	        		//没有缓存时
	        		//System.out.println("******一、二区域未缓存**********");
	        		list = houseInfoDao.getHouseInfoLike(getHouseInfoDto);
	            	total=houseInfoDao.getHouseInfoLikeCount(getHouseInfoDto);
	            	if (isNeedToRedis(getHouseInfoDto.getPageNumber(), true)) {
	            		//页数小于最大缓存页数时，可进行缓存！
	            		RedisUtils.set(SerializeUtil.serialize(listLikeKey), SerializeUtil.serialize(list),expired_Time);
	            		RedisUtils.set(SerializeUtil.serialize(listLikeCountKey), SerializeUtil.serialize(total),expired_Time);
	            	}
				}else {
					//System.out.println("******一二级区域有缓存**********");
					list = (List<HouseInfoPropertyAndFavouriteStatusVO> )SerializeUtil.unserialize(listLikeBs);
	            	total=(int) SerializeUtil.unserialize(listLikeCountBs);
				}
	        }
		}
        //【修改 2015年9月18日】添加是否已经收藏的标记
//        【修改 2015年9月30日】去除已收藏功能
//        for (HouseInfoNewAndFavouriteStatusVO vo : list) {
//        	MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
//        	myBrowseInfoDto.setBrowseFavoriteStyle(MyBrowseInfoDto.Favorites);
//        	myBrowseInfoDto.setHouseInfo_id(vo.getId());
//        	myBrowseInfoDto.setMember_id(getHouseInfoDto.getMember_id());
//			vo.setFavouriteStatus(brosweHouseInfoService.isHouseInfoInMyFavorite(myBrowseInfoDto));
//		}
        pages.setList(list);
        pages.setTotalCount(total);        
        pages.setPageOffset(getHouseInfoDto.getPageOffset());
        pages.setPageSize(getHouseInfoDto.getPageSize());
        
        return (Pager<T>) pages;
    }
    
    // 新增逻辑：如果区域有子节点就需要模糊查询，否则是根据id精确查询
	@Override
	public int getHouseInfoCountByArea(long area_id) {
		
		String key = "HouseInfoCountByArea"+area_id;
		int expired_Time = SettingUtils.get().getRedis_expire_10Min();
        byte[] bs = RedisUtils.get(SerializeUtil.serialize(key));
        int count = 0;
        if (bs==null) {
        	//区域是否有子节点
        	List<Area> findChildren = areaService.findChildren(area_id);
        	if(findChildren.isEmpty()){
            	count = houseInfoDao.getHouseInfoCountByAreaCdt(String.valueOf(area_id));
        	}else{
        		String area_idString = ","+String.valueOf(area_id)+",";
            	count = houseInfoDao.getHouseInfoCountByAreaLike(area_idString);
        	}
        	RedisUtils.set(SerializeUtil.serialize(key), SerializeUtil.serialize(count),expired_Time);
		}else {
			count = (int) SerializeUtil.unserialize(bs);
		}
		return count;
	}

	@Override
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCity(long area_id) {
		List<Area> areas = new ArrayList<Area>();
		Area parent = areaService.load(area_id);
		if (parent != null) {
			areas = new ArrayList<Area>(areaService.findChildren(area_id));
		} else {
			areas = areaService.findRoots();
		}
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		for (Area area : areas) {
			GetAreaHouseCountByCityVO areaHouseCountByCityVO = new GetAreaHouseCountByCityVO();
			areaHouseCountByCityVO.setAreaId(area.getId());
			areaHouseCountByCityVO.setAreaName(area.getName());
			areaHouseCountByCityVO.setHouseInfoCount(getHouseInfoCountByArea(area.getId()));
			list.add(areaHouseCountByCityVO);
		}
		return list;
	}
	
	@Override
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id) {
		
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		String Key = "getAreaHouseCountByCityV2"+area_id;
		int expired_Time=300;
        byte[] res = RedisUtils.get(SerializeUtil.serialize(Key));
        if (res == null ) {
        	list = houseInfoDao.getAreaHouseCountByCityV2(area_id);
        	byte[] serialize = SerializeUtil.serialize(list);
			RedisUtils.set(SerializeUtil.serialize(Key), serialize,expired_Time);
		}else {
			list = (List<GetAreaHouseCountByCityVO>)SerializeUtil.unserialize(res);
		}
		return list;
	}

	@Override
	public String getMobileByHouseId(long houseInfo_id) {
		String mobile = houseInfoDao.getMobileByHouseId(houseInfo_id);
		return mobile;
	}

    @Override
    public int getCountByMobile(String mobile) {
        return houseInfoDao.getCountByMobile(mobile);
    }
    @Override
	public boolean isBalanceEnoughToBrowse(float balance, long houseInfo_id) {
		Float infoPrice = new Float(houseInfoDao.getHouseInfoPrice(houseInfo_id));
		int enoughOrNot = new Float(balance).compareTo(infoPrice);
		//2者作比较，balace大于或等于infoPrice时，即余额充足，此时enoughOrNot>0
		return enoughOrNot<0?false:true;
	}

	@Override
	public boolean isHouseInfoBought(MyBrowseInfoDto myBrowseInfoDto) {
		return brosweHouseInfoService.isHouseInfoInMyBrowse(myBrowseInfoDto);
	}

	@Override
	public HouseInfoProperty browseHouseInfoByHouseId(long houseInfo_id) {
		return houseInfoDao.browseHouseInfoByHouseId(houseInfo_id);
	}

	@Override
	public Member getHouseInfoMember(HouseInfoProperty houseInfoProperty, boolean isBought) {
		//首先检查出售方式
		Member member = null;
		HouseInfoProperty tempHouseInfoProperty = new HouseInfoProperty();
		tempHouseInfoProperty.setId(houseInfoProperty.getId());
		int readTime = houseInfoProperty.getReadTime();
		if (houseInfoProperty.getSaleWay().compareTo(HouseInfoProperty.HouseInfo_SaleWay.CUSTOMER)==0) {
			//自定义房源
			member = houseInfoDao.getHouseMember(houseInfoProperty.getId());
			//如果未购买，查看后，房源的readTime+1
			//readTime+1后如果=3，更改为系统房源
			//如果已经购买，则readTime不增加
			if (!isBought) {
				if (readTime+1 >= 3) {
					//更改CUSTOMERINFO_SALEWAY方式为SYSTEM
					tempHouseInfoProperty.setInfoPrice(10f);
					tempHouseInfoProperty.setSaleWay(HouseInfoProperty.HouseInfo_SaleWay.SYSTEM);
					tempHouseInfoProperty.setReadTime(readTime+1);
				}else {
					//readTime更新为readTime+1
					tempHouseInfoProperty.setReadTime(readTime+1);
				}
				tempHouseInfoProperty.setAvailable(true);
				houseInfoDao.updateHouseInfoWhenBrowse(tempHouseInfoProperty);
			}
		}else if (houseInfoProperty.getSaleWay().compareTo(HouseInfoProperty.HouseInfo_SaleWay.SYSTEM)==0) {
			//系统房源
			member = memberService.getSystemMember();
			if (!isBought) {
				//readTime更新为readTime+1
				tempHouseInfoProperty.setReadTime(readTime+1);
				tempHouseInfoProperty.setAvailable(true);
				houseInfoDao.updateHouseInfoWhenBrowse(tempHouseInfoProperty);
			}
		}
		return member;
	}

	@Override
	public void updateBalanceInfo(Member member, float updateAmount, int increaseOrReduce) {
		UpdateBalanceDto updateBalanceDto = new UpdateBalanceDto();
		updateBalanceDto.setMember(member);
		updateBalanceDto.setUpdateAmount(updateAmount, increaseOrReduce);
		memberService.updateBalance(updateBalanceDto);
	}

	@Override
	public void addIncomeExpenseAndBrowseInfo(Member houseInfoMember, Member loginMember, HouseInfoProperty houseInfoProperty) {
		
		if(houseInfoMember.getId()==memberService.getSystemMember().getId()){
			//系统房源，系统增加一条10元收入记录
			Income income = new Income();
			income.setAmount(Float.parseFloat(String.valueOf(houseInfoProperty.getInfoPrice())));
			income.setIncomeFrom(loginMember.getId());//收入来源ID为当前登陆账号
			income.setMember(houseInfoMember);//收入所属为房源信息人
			income.setIncomeType(INCOME_TYPE.dealIncome);
			income.setHouseInfo_id(houseInfoProperty.getId());
			incomeService.addIncomeInfo(income);
		}else{
			//非系统房源，增加两条收入记录，房源拥有者增加70%，系统增加30%
			Income income = new Income();
			income.setAmount(Float.parseFloat(String.valueOf(houseInfoProperty.getInfoPrice()*0.3)));
			income.setMember(memberService.getSystemMember());//收入所属为系统
			income.setIncomeFrom(loginMember.getId());//收入来源ID为当前登陆账号
			income.setIncomeType(INCOME_TYPE.dealIncome);
			income.setHouseInfo_id(houseInfoProperty.getId());
			incomeService.addIncomeInfo(income);
			//
			income.setAmount(Float.parseFloat(String.valueOf(houseInfoProperty.getInfoPrice()*0.7)));
			income.setMember(houseInfoMember);
			incomeService.addIncomeInfo(income);
		}
		
		//支出明细
		Expenses expenses = new Expenses();
		expenses.setAmount(String.valueOf(houseInfoProperty.getInfoPrice()));
		expenses.setExpensesTo(houseInfoMember.getId());//支出对象为房源信息人
		expenses.setMember(loginMember);//支出所属为登陆会员
		expenses.setExpensesType(EXPENSES_TYPE.dealExpense.getCode());
		expenses.setHouseInfo_id(houseInfoProperty.getId());
		expenses.setHouseType(HouseTypeEnum.PROPERTY.getCode());
		expensesService.addExpenseInfo(expenses);
		
		//加入查看房源信息
		BrowseFavoriteInfo browseFavoriteInfo = new BrowseFavoriteInfo();
		browseFavoriteInfo.setCreateDate(new Date());
		browseFavoriteInfo.setModifyDate(new Date());
		browseFavoriteInfo.setMember(loginMember);
		browseFavoriteInfo.setHouseInfoId(houseInfoProperty.getId());
		browseFavoriteInfo.setHouseStyle(BrowseFavoriteInfo.HouseStyle.PROPERTY);
		browseFavoriteInfo.setBrowseFavoriteStyle(BrowseFavoriteStyle.Browse);
		brosweHouseInfoService.addBrowseHouseInfo(browseFavoriteInfo);
	}

	@Override
	public BrowseHouseInfoVO getBrowseHouseInfoVO(long houseInfo_id, HttpServletRequest request) {
		BrowseHouseInfoVO browseHouseInfoVO = new BrowseHouseInfoVO();
		Member loginMember = memberService.getCurrent(request);//登录账号
		boolean isMine = false; //是否自己发布的房源
		boolean isBought = false;//是否购买
		boolean isBalanceEnough = false;//余额是否充足
		HouseInfoProperty houseInfoProperty = null;//房源信息
		MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
		myBrowseInfoDto.setHouseInfo_id(houseInfo_id);
		myBrowseInfoDto.setMember_id(loginMember.getId());
		myBrowseInfoDto.setBrowseFavoriteStyle(MyBrowseInfoDto.Browse);
		//首先判断是否是自己发布的房源
		Member isMymember = houseInfoDao.getHouseMember(houseInfo_id);
		if (new Long(isMymember.getId()).compareTo(new Long(loginMember.getId()))==0) {
			//自己发布的房源时，直接反回houseInfo
			isMine = true;
			houseInfoProperty = new HouseInfoProperty();
			houseInfoProperty = browseHouseInfoByHouseId(houseInfo_id);
			houseInfoProperty.setMember(isMymember);
		}else {
			isBought = isHouseInfoBought(myBrowseInfoDto);
			if (isBought) {
				//已经购买
				houseInfoProperty = new HouseInfoProperty();
				houseInfoProperty = browseHouseInfoByHouseId(houseInfo_id);
				Member houseInfoMember = getHouseInfoMember(houseInfoProperty, isBought);
				houseInfoProperty.setMember(houseInfoMember);
			}else {
				//未购买时，先检查余额
				float balance = loginMember.getBalance();//余额信息
				//2015年11月13日10:24修正【余额应为现有余额减去冻结余额】
				balance = balance - getFrozenAmount(loginMember);
				isBalanceEnough = isBalanceEnoughToBrowse(balance, houseInfo_id);
				HouseInfoProperty tempInfo = new HouseInfoProperty();
				tempInfo = browseHouseInfoByHouseId(houseInfo_id);
				houseInfoProperty = new HouseInfoProperty();
				houseInfoProperty.setId(houseInfo_id);
				houseInfoProperty.setInfoPrice(tempInfo.getInfoPrice());
			}
		}
		browseHouseInfoVO.setMine(isMine);
		browseHouseInfoVO.setBought(isBought);
		browseHouseInfoVO.setBalanceEnough(isBalanceEnough);
		browseHouseInfoVO.setHouseInfoProperty(houseInfoProperty);
		return browseHouseInfoVO;
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public Pager<HouseInfoProperty> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto) {
        getHouseInfoDto.setPageOffset((getHouseInfoDto.getPageNumber()-1)
                *getHouseInfoDto.getPageSize());
        Pager<HouseInfoProperty> pages=new Pager<HouseInfoProperty>();

        List<HouseInfoProperty> list=houseInfoDao.getReleaseHouseInfo(getHouseInfoDto);
        int total=houseInfoDao.getReleaseHouseInfoCount(getHouseInfoDto);
        pages.setList(list);
        pages.setTotalCount(total);
        pages.setPageOffset(getHouseInfoDto.getPageOffset());
        pages.setPageSize(getHouseInfoDto.getPageSize());
        return pages;
    }
    

    
    @Override
    public Area getHouseInfoArea(long houseInfo_id) {
    	return houseInfoDao.getHouseInfoArea(houseInfo_id);
    }
    
    @Override
    public int getTodayHouseInfoCount(int area_id) {
    	return houseInfoDao.getTodayHouseInfoCount(area_id);
    }
    
    @Override
    public List<TopTenRankingVO> getTop10Ranking() {
    	List<Map<String, Object>> rankList = houseInfoDao.getTop10Ranking();
    	if (rankList.size()>10) {
			rankList = rankList.subList(0, 10);
		}
    	List<TopTenRankingVO> rankVOList = new ArrayList<TopTenRankingVO>();
    	for (Map<String, Object> map : rankList) {
    		TopTenRankingVO rankingVO = new TopTenRankingVO();
    		long memberId = Long.valueOf(map.get("member_id").toString());
    		rankingVO.setMemberName(memberService.load(new Long(memberId)).getUsername());
    		rankingVO.setReleaseCount(Integer.parseInt(map.get("count").toString()));
    		rankVOList.add(rankingVO);
		}
    	return rankVOList;
    }
    
    @Override
    public List<HouseInfoProperty> getLatestHouseInfoVO() {
    	List<HouseInfoProperty> latestList = new ArrayList<HouseInfoProperty>();
    	latestList = houseInfoDao.getLatestHouseInfo();
    	return latestList;
    }
    
    @Override
    public HouseInfoProperty loadHouseInfo(long id) {
    	return houseInfoDao.loadHouseInfo(id);
    }
    
    @Override
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto) {
    	return houseInfoDao.isHouseInfoExist(houseInfoExistDto);
    }

	@Override
	public boolean isHouseInfoExistRemoteInvacation(HouseInfoExistDto houseInfoExistDto) {
		HttpHost targetHost = new HttpHost("dean.fangdd.fdd", 80, "http");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCredentialsProvider().setCredentials(
		new AuthScope(targetHost.getHostName(), targetHost.getPort()),
		new UsernamePasswordCredentials("fangdd_dean", "s+YbZftGL8tl"));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);
		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

		String url ="http://dean.fangdd.fdd/house/isExistsHouseByOwner/v_1/mobile/ban/roomNumber";
		url =url.replace("mobile", houseInfoExistDto.getMobile());
//		url =url.replace("ban", houseInfoExistDto.getBan());
//		url =url.replace("roomNumber", houseInfoExistDto.getRoomNumber());
		
		HttpPost httpPost = new  HttpPost(url);

		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		String result="";
		try {
			HttpResponse response = httpclient.execute(targetHost, httpPost, localcontext);
			HttpEntity entity = response.getEntity();
			result=EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		if(!result.isEmpty()){
			if(result.contains("true")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean isHouseInfoExistRemoteInvacationV2(HouseInfoExistDto houseInfoExistDto) {
		HttpHost targetHost = new HttpHost("dean.fangdd.fdd", 80, "http");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCredentialsProvider().setCredentials(
		new AuthScope(targetHost.getHostName(), targetHost.getPort()),
		new UsernamePasswordCredentials("fangdd_dean", "s+YbZftGL8tl"));
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);
		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

		String url ="http://dean.fangdd.fdd/house/pb/get_phone_related_house_info/mobile";
		url =url.replace("mobile", houseInfoExistDto.getMobile());
		HttpPost httpPost = new  HttpPost(url);

		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		String result="";
		try {
			HttpResponse response = httpclient.execute(targetHost, httpPost, localcontext);
			HttpEntity entity = response.getEntity();
			result=EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		if(!result.isEmpty()){
			if(result.contains("\"exist\":true")&&result.contains("\"offline\":false")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
    
    @Override
    public String selectAreaByName(String name) {
    	return houseInfoDao.selectAreaByName(name);
    }

	@Override
	public HouseInfoProperty loadHouseInfoWithMember(long id) {
		return houseInfoDao.loadHouseInfoWithMember(id);
	}
	
	@Override
	public List<AgentDynamicVO> getAgentDynamicList() {
		List<AgentDynamicVO> agentList = new ArrayList<AgentDynamicVO>();
		List<WithDraw> wList = withDrawService.getLatesWithDraw();
		List<HouseInfoProperty> hList = getLatestHouseInfoVO();
		AgentDynamicVO vo = null;
		for (WithDraw withDraw : wList) {
			vo = new AgentDynamicVO();
			vo.setWithdrawOrNot(true);
			vo.setWithDraw(withDraw);
			agentList.add(vo);
		}
		for (HouseInfoProperty houseInfoProperty : hList) {
			vo = new AgentDynamicVO();
			vo.setWithdrawOrNot(false);
			vo.setHouseInfoProperty(houseInfoProperty);
			agentList.add(vo);
		}
		return agentList;
	}
	
	@Override
	public boolean isMobileOk(String mobile) {
		return !isAgentMobile(mobile);
	}
	


	@Override
	public boolean isAgentMobileRemote(String mobile){
		
		String url = "http://agent.esf.fangdd.com/agent/mobile/isExist";
		url = url.replaceAll("mobile", mobile);
		String httpRequestGet = CommonUtils.httpRequestGet(url);
		return httpRequestGet.indexOf("true")!=-1;
	}

	@Override
	public boolean isAgentMobile(String mobile) {
		return houseInfoDao.isAgentMobile(mobile)||isAgentMobileRemote(mobile);
	}
	
	@Override
	public boolean validateHouseInfo(HouseInfoProperty houseInfoProperty) {
		if (!isMobileOk(houseInfoProperty.getMobile())) {
			return true;//重复了
		}
		//2015年11月23日16:47【远程接口访问是否经纪人】
//		GetAgentByPhoneRequest request = new GetAgentByPhoneRequest();
//		request.setPhone(houseInfoProperty.getMobile());
//		AgentResponse response = 
		
    	//2015年9月19日10:41 【修改房屋信息是否已经存在的判断】
    	HouseInfoExistDto houseInfoExistDto = new HouseInfoExistDto();
    	houseInfoExistDto.setMobile(houseInfoProperty.getMobile());
    	//2015年11月5日10:52【直接手机号判重】
    	boolean isExisted2 = false;
//    	try {
//    		isExisted2 =isHouseInfoExistRemoteInvacationV2(houseInfoExistDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        return isExisted2;
	}
	
	@Override
	public StateStatusVO checkStateStatus(StateStatusDto dto) {
		StateStatusVO vo = new StateStatusVO();
		List<State> list = new ArrayList<State>();
		list = houseInfoDao.isStated(dto.getHouseInfo_id());
		if (list.size()>0) {
			vo.setCanBeStated(false);
			vo.setStatusFailReason("该房源您已申诉！");
			vo.setCurrentStatus(list.get(0).getStateStatus().getOutername());
			vo.setStateContent(list.get(0).getStateContent());
		}else{
			boolean isStatedExpired = houseInfoDao.isStatedExpired(dto.getHouseInfo_id());
			if (isStatedExpired) {
				vo.setCanBeStated(false);
				vo.setStatusFailReason("该房源已过3天申诉期！");
			}else{
				//2015年11月7日12:11更新【不限制条数】
//				boolean isEnough = houseInfoDao.isStatedCountEnough(dto.getMember_id());
//				if (isEnough) {
//					vo.setCanBeStated(false);
//					vo.setStatusFailReason("当天您的申诉房源量已满5条！");
//				}else {
					vo.setCanBeStated(true);
					vo.setStatusFailReason("该房源您可以申诉！");
//				}
			}
		}
		return vo;
	}
	
	@Override
	public boolean addState(State state) {
		return houseInfoDao.addState(state);
	}
	
	@Override
	public List<Community> getTopTenCommunity(String keyWords) {
		return houseInfoDao.getTopTenCommunity(keyWords);
	}
	
	private float getFrozenAmount(Member member){
		IncomeAmountVO vo = incomeService.getIncomeAmountByDate(member.getId());
		vo.setBalance(member.getBalance());
		if (vo.getYesterdaySum()>vo.getBalance()) {
			vo.setYesterdaySum(vo.getBalance());
		}
		return vo.getYesterdaySum();
	}

	@Override
	public BonusVO getBonusInfo(BonusProcessDto dto) {
		BonusVO vo = new BonusVO();
		ExtraAward award = houseInfoDao.getCurrentBonusStatus(dto);
		vo.setAward(award);
		//当月自发数
		int count = houseInfoDao.getSelfCountByMonth(dto);
//		System.out.println(dto.getYearMonth()+"==>"+count);
		vo.setCurrentSelfCount(count);
		//当月1级数
		count = houseInfoDao.getlevel1CountByMonth(dto);
		vo.setLevel1Count(count);
		//当月2级数
		count = houseInfoDao.getlevel2CountByMonth(dto);
		vo.setLevel2Count(count);
		int yearMonth = dto.getYearMonth();
		int year = yearMonth/100;
		int month = yearMonth%100;
		if(month!=1){
			yearMonth = yearMonth-1;
		}else{
			yearMonth = (year-1)*100+12;
		}
		dto.setYearMonth(yearMonth);
		//上月自发数
		count = houseInfoDao.getSelfCountByMonth(dto);
//		System.out.println(dto.getYearMonth()+"==>"+count);
		vo.setLastSelfCount(count);
		return vo;
	}


	@Override
	public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto) {
		// TODO Auto-generated method stub
		return houseInfoDao.getTopTenCommunityV2(getCommunityV2Dto);
	}


	@Override
	public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto) {
		return houseInfoDao.getLevel1Detail(dto);
	}
	
	@Override
	public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto) {
		return houseInfoDao.getLevel2Detail(dto);
	}
	
	@Override
	public boolean isHouseMemberFromSameCity(HttpServletRequest request, Long areaId2) {
		return areaService.isAreaFromSame(request, areaId2);
	}

}
