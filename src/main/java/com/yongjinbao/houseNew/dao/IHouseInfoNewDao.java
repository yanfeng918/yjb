package com.yongjinbao.houseNew.dao;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.finance.entity.ExtraAward;
import com.yongjinbao.houseNew.dto.BonusProcessDto;
import com.yongjinbao.houseNew.dto.GetCommunityV2Dto;
import com.yongjinbao.houseNew.dto.GetHouseInfoDto;
import com.yongjinbao.houseNew.dto.HouseInfoExistDto;
import com.yongjinbao.houseNew.entity.HouseInfoNew;
import com.yongjinbao.houseNew.entity.State;
import com.yongjinbao.houseNew.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseNew.vo.HouseInfoNewAndFavouriteStatusVO;
import com.yongjinbao.houseNew.vo.LevelDetailVO;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by yanfeng on 2015/8/18.
 */
public interface IHouseInfoNewDao extends IBaseDao<HouseInfoNew,Integer> {
	
	
	/**
	 * 查询 地区 售价 面积 小区名称等等
	 */
	//public List<HouseInfoNew> getHouseInfoNew(GetHouseInfoDto getHouseInfoDto);
	
    /**
     * 查询 地区 售价 面积 小区名称等等条件筛选后总数查询
     */
    //public int getHouseInfoCount(GetHouseInfoDto getHouseInfoDto);
	
	/**
	 * 查询 地区 售价 面积 小区名称等等（这个区域没有子节点的）例如：上海市黄浦区南京西路的房源
	 */
	public List<HouseInfoNewAndFavouriteStatusVO> getHouseInfoCdt(GetHouseInfoDto getHouseInfoDto);
	
    /**
     * 查询 地区 售价 面积 小区名称等等条件筛选后总数查询  例如：上海市黄浦区南京西路的房源总数
     */
    public int getHouseInfoCdtCount(GetHouseInfoDto getHouseInfoDto);
	
	/**
	 * 查询 地区 售价 面积 小区名称等等（这个区域有子节点的  ）例如：上海市的房源
	 */
	public List<HouseInfoNewAndFavouriteStatusVO> getHouseInfoLike(GetHouseInfoDto getHouseInfoDto);
	
    /**
     * 查询 地区 售价 面积 小区名称等等条件筛选后总数查询  例如：上海市的房源总数
     */
    public int getHouseInfoLikeCount(GetHouseInfoDto getHouseInfoDto);

    
    /**
     * (模糊查询-根据房源区域的ID查询房源数)这个区域是有子节点的
     */
    public int getHouseInfoCountByAreaLike(String area_id);
    
    /**
     * (精确条件查询-根据房源区域的ID查询房源数)这个区域是没有子节点的
     */
    public int getHouseInfoCountByAreaCdt(String area_id);
    
    /**
	 * 根据房源id获取会员手机号码
	 */
    public String getMobileByHouseId(long houseInfo_id);
    
    /**
     * 根据手机号查询房源业主是否已经存在
     */
    public int getCountByMobile(String mobile);
    
    /**
     * 查询用户发布的房源信息
     */
    public List<HouseInfoNew> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto);
    
    /**
     * 查询用户发布的房源信息
     */
    public int getReleaseHouseInfoCount(GetHouseInfoDto getHouseInfoDto);
    /**
     * 获取房源的数据价钱
     */
    public float getHouseInfoPrice(long houseInfo_id);
    
    public HouseInfoNew browseHouseInfoByHouseId(long houseInfo_id);
    
    /**
     * 获取房源信息发布会员
     */
    public Member getHouseMember(long houseInfo_id);
    
    /**
     * 查看数据时更新房源信息
     */
    public boolean updateHouseInfoWhenBrowse(HouseInfoNew houseInfoNew);
    
    /**
     * 根据房源id获取关联区域信息
     */
    public Area getHouseInfoArea(long houseInfo_id);
    
    /**
     * 获取今日房源数
     */
    public int getTodayHouseInfoCount(int area_id);
    
//    /**
//     * 修改自己发布的房源【只能修改价格或修改全部信息】
//     */
//    public boolean updateMyHouseInfo(HouseInfoNew houseInfoNew);
    /**
     * 获取牛人榜会员
     */
    public List<Map<String, Object>> getTop10Ranking();
    
    /**
     * 获取最新10套房源
     */
    public List<HouseInfoNew> getLatestHouseInfo();
    
    /** 根据houseInfo_id加载房源**/
    public HouseInfoNew loadHouseInfo(long id);
    
    /** 新增房源时是否已经在本地房源库中存在**/
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto);
    
    /** **/
    public String selectAreaByName(String name);
    
    /**
     * 根据所选城市获取区域及其房源数V2
     */
    public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id);
    
    public HouseInfoNew loadHouseInfoWithMember(long id);
    
    /** 【导入时手机号是否可以】 **/
    public boolean isMobileOk(String mobile);
    /**
     * 查看手机号是否在本地数据库中重复
     */
    public List<HouseInfoNew> isLocalMobile(String mobile);
    /**
     * 查看手机号是否是经纪人手机号
     */
    public boolean isAgentMobile(String mobile);
    
    /** 是否已经申诉 **/
    public List<State> isStated(long houseInfo_id);
    
    /** 申诉时间是否过期，即是否大于3天 **/
    public boolean isStatedExpired(long houseInfo_id);
    
    /** 是否超过当天最大申诉量5次 **/
    public boolean isStatedCountEnough(long member_id);
    
    /** 添加申诉信息 **/
    public boolean addState(State state);
    
    /** 根据关键字获取楼盘信息 **/
    public List<Community> getTopTenCommunity(String keyWords);
    
    /** 根据关键字获取楼盘信息 **/
    public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto);
    
    /** selfCount **/
    public int getSelfCountByMonth(BonusProcessDto dto);
    
    /** level1Count **/
    public int getlevel1CountByMonth(BonusProcessDto dto);
    
    /** level2Count **/
    public int getlevel2CountByMonth(BonusProcessDto dto);
    
    /** level1会员发房明细 **/
    public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto);
    
    /** level2会员发房明细 **/
    public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto);
    
    /** 查询月的奖励是发放状态 **/
    public ExtraAward getCurrentBonusStatus(BonusProcessDto dto);
    
    /** 修改房源状态为待审核**/
    public boolean updateHouseToApplyStatus(HouseInfoNew houseInfoNew);
    
   /**  激活房源时添加一条激活记录 **/
    public boolean addActiveRecord(long houseInfo_id);
    
}

