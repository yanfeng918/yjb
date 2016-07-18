package com.yongjinbao.houseNew.service;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.houseNew.dto.*;
import com.yongjinbao.houseNew.entity.HouseInfoNew;
import com.yongjinbao.houseNew.entity.State;
import com.yongjinbao.houseNew.vo.*;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yanfeng on 2015/8/18.
 */
public interface IHouseInfoNewService extends IBaseService<HouseInfoNew,Integer>{

	public <T>Pager<T> getHouseInfo(GetHouseInfoDto getHouseInfoDto);
	/**
     * 根据区域Id获取其区域的房源数
     */
	public int getHouseInfoCountByArea(long area_id);
	
	/**
     * 根据所选城市获取区域及其房源数
     */
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCity(long area_id);
	
	/**
     * 根据所选城市获取区域及其房源数V2
     */
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id);
	
	/**
	 * 根据房源id获取会员手机号码
	 */
	public String getMobileByHouseId(long houseInfo_id);
	/**
     * 根据手机号查询房源业主是否已经存在
     */
    public int getCountByMobile(String mobile);	
	/**
	 * 余额是否充足
	 * @param houseInfo_id
	 */
	public boolean isBalanceEnoughToBrowse(float balance, long houseInfo_id);
	
	/**
	 * 当前房源信息是否已经购买
	 */
	public boolean isHouseInfoBought(MyBrowseInfoDto myBrowseInfoDto);
	
	/**
	 * 查看数据操作时获取房源的全部信息
	 */
	public HouseInfoNew browseHouseInfoByHouseId(long houseInfo_id);
	
	/**
	 * 购买数据时添加收支明细和我的查看数据
	 */
	public void addIncomeExpenseAndBrowseInfo(Member houseMember, Member loginMember, HouseInfoNew houseInfoNew);
	
	/**
	 * 获取房源信息所属人员
	 * @param houseInfoNew 房源信息
	 * @param isBought 是否购买过【true时直接取房源发布者】
	 * @return
	 */
	public Member getHouseInfoMember(HouseInfoNew houseInfoNew, boolean isBought);
	
	/**
	 * 【查看数据】时更改账户余额信息
	 * @param member 操作会员对象
	 * @param updateAmount 更改金额
	 * @param increaseOrReduce 增加或减少
	 */
	public void updateBalanceInfo(Member member, float updateAmount, int increaseOrReduce);
	/**
	 * 获取房源查看操作VO
	 */
	public BrowseHouseInfoVO getBrowseHouseInfoVO(long houseInfo_id, HttpServletRequest request);
	
    /**
     * 查询我的发布发源
     */
    public <T>Pager<T> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto);
    

	
	/**
	 * 获取房源关联区域信息
	 */
	public Area getHouseInfoArea(long houseInfo_id);
	
	/**
     * 获取今日房源数
     */
    public int getTodayHouseInfoCount(int area_id);
    
//    /**
//     * 修改我发布的房源
//     */
//    public boolean updateMyReleaseInfo(HouseInfoNew houseInfoNew);
    
    /** 获取牛人榜【前10】 **/
    public List<TopTenRankingVO> getTop10Ranking();
    
    /** 获取前10房源信息 **/
    public List<HouseInfoNew> getLatestHouseInfoVO();
    
    /** 根据houseInfo_id加载房源**/
    public HouseInfoNew loadHouseInfo(long id);
    
    /** 新增房源时是否已经在本地房源库中存在**/
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto);
    
    /** 新增房源时是否已经在本地房源库中存在**/
    public boolean isHouseInfoExistRemoteInvacation(HouseInfoExistDto houseInfoExistDto);
    
    /** 新增房源时是否已经在本地房源库中存在[下架房源不参与去重]**/
    public boolean isHouseInfoExistRemoteInvacationV2(HouseInfoExistDto houseInfoExistDto);
    
    public String selectAreaByName(String name);
    
    public HouseInfoNew loadHouseInfoWithMember(long id);
    
    public List<AgentDynamicVO> getAgentDynamicList();
    
    /** 【导入时手机号是否可以】 **/
    public boolean isMobileOk(String mobile);



	/**
	 * 查看手机号是否是经纪人手机号
	 */
	public boolean isAgentMobile(String mobile);
	
	/**
	 * 查看手机号是否是经纪人手机号
	 */
	public boolean isAgentMobileRemote(String mobile);
	
	/** 添加房源时房源去重验证 **/
	public boolean validateHouseInfo(HouseInfoNew houseInfoNew);
	
	/** 申诉时检查房源状态 **/
	public StateStatusVO checkStateStatus(StateStatusDto stateStatusDto);
	

    
    /** 根据关键字获取楼盘信息 **/
    public List<Community> getTopTenCommunity(String keyWords);
    
    /** 根据关键字获取楼盘信息 **/
    public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto);
    
    /**获取奖励信息**/
    public BonusVO getBonusInfo(BonusProcessDto dto);
    
    /** level1会员发房明细 **/
    public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto);
    
    /** level2会员发房明细 **/
    public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto);
    
    /** 发房人和发房地址是否同一区域 **/
    public boolean isHouseMemberFromSameCity(HttpServletRequest request, Long areaId2);

	/** 获取已经购买的房源信息 **/
	HouseInfoNew getBoughtHouseInfo(long houseInfo_id, HttpServletRequest request);

	// 判断是否购买该房源
	Boolean isBoughtHouseInfo(long houseInfo_id, HttpServletRequest request);

}
