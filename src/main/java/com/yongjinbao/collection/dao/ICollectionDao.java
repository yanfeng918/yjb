package com.yongjinbao.collection.dao;

import java.util.List;

import com.yongjinbao.collection.dto.GetCollectionDto;
import com.yongjinbao.collection.dto.GetMyResponseDto;
import com.yongjinbao.collection.dto.ResponseCollectionGto;
import com.yongjinbao.collection.entity.Collection;
import com.yongjinbao.collection.vo.CollectionCountByAreaVO;
import com.yongjinbao.collection.vo.MyResponseHouseInfoVO;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * @author mashenwei
 * @since 2015/09/14
 */
public interface ICollectionDao extends IBaseDao<Collection, Long> {
	
	/** 【房源征集】添加征集需求  **/
	public boolean addCollection(Collection collection);
	
	/** 【房源征集】 获取征集列表【区域不包含子区域时精确查询】 **/
	public List<Collection> getAllCollectionCdtList(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】 获取征集列表【区域包含子区域时模糊查询】 **/
	public List<Collection> getAllCollectionLikeList(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】根据版块精确查询征集列表总数 **/
	public int getAllCollectionCdtListCount(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】根据版块模糊查询征集列表总数 **/
	public int getAllCollectionLikeListCount(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】 获取我的征集列表 **/
	public List<Collection> getMyCollectionList(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】我的征集列表总数 **/
	public int getMyCollectionListCount(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】 根据征集ID获取我的征集回答数**/
	public int getResponseCountByCollectId(GetMyResponseDto myResponseDto);
	
	/** 【房源征集】 获取我的征集中回答的房源信息**/
	public List<HouseInfo> getResponseHouseInfoByCollectId(GetMyResponseDto myResponseDto);
	
	/** 【房源征集】 提交房源时写关联信息 **/
	public boolean addResponseToCollection(ResponseCollectionGto responseCollectionGto);
	
	/** 【房源征集】 获取我提交的房源信息列表**/
	public List<MyResponseHouseInfoVO> getMyResponsedHouseInfoList(GetMyResponseDto myResponseDto);
	
	/** 【房源征集】 获取我提交的房源信息数量**/
	public int getMyResponsedHouseInfoListCount(GetMyResponseDto myResponseDto);
	
	/** 【房源征集】 根据区域id获取相应征集数**/
	public int getCollectionCountByAreaId(long area_id);
	
	/** 【房源征集】 个人中心获取最新10条征集**/
	public List<Collection> getLatestCollection();
	
	/** 【房源征集】 个人中心获取今日征集数**/
	public int getTodayCollectionCount(int area_id);
	
	/** 【从房源供求中进入查看房源时】更新提交的房源回答次数**/
	public boolean updateReadTime(ResponseCollectionGto responseCollectionGto);
	
	/** 获取我发布的房源的数量 **/
    public int getMyReleaseCount(long member_id);
    
//    /** 2015年10月1日 删除我的征集**/
//    public boolean delMyCollection(long collection_id);
//    
//    /** 2015年10月1日 删除我的征集关联信息**/
//    public void delMyCollectionResponse(long collection_id);
    
    /**【2015年10月4日获取区域及其征集数，新方法 BY YANGFENG】**/
    public List<CollectionCountByAreaVO> getCollectionCountByAreaIdV2(long area_id);
    
    /** 2015年10月7日 删除征集变更为关闭征集**/
    public boolean closeMyCollection(long collection_id);
	
}
