package com.yongjinbao.collection.service;

import java.util.List;

import com.yongjinbao.collection.dto.AddCollectionHouseInfoDto;
import com.yongjinbao.collection.dto.GetCollectionDto;
import com.yongjinbao.collection.dto.GetMyResponseDto;
import com.yongjinbao.collection.dto.ResponseCollectionGto;
import com.yongjinbao.collection.entity.Collection;
import com.yongjinbao.collection.vo.CollectionCountByAreaVO;
import com.yongjinbao.houseinfo.dto.HouseInfoExistDto;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

public interface ICollectionService extends IBaseService<Collection, Long> {

	/** 【房源征集】添加征集需求  **/
	public boolean addCollection(Collection collection);
	
	/** 【房源征集】 获取所有征集数据列表 **/
	public <T>Pager<T> getAllCollectionList(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】 获取单条记录**/
	public Collection load(long id);
	
	/** 【房源征集】 为征集提交房源数据**/
	public boolean addResponseToCollection(AddCollectionHouseInfoDto addCollectionHouseInfoGto);
	
	/** 【房源征集】 根据征集id获取回答内容**/
	public <T>Pager<T> getResponseHouseInfoByCollectId(GetMyResponseDto myResponseDto);

	/** 【房源征集】 根据征集ID获取我的征集回答数**/
	public int getResponseCountByCollectId(GetMyResponseDto myResponseDto);
	
	/** 【房源征集】 获取我的征集列表**/
	public <T>Pager<T> getMyCollectionList(GetCollectionDto getCollectionDto);
	
	/** 【房源征集】 获取我回答【提交】的房源信息列表**/
	public <T>Pager<T> getMyResponsedHouseInfoList(GetMyResponseDto myResponseDto);
	
	/** 【房源征集】 根据区域id获取相应征集数**/
	public int getCollectionCountByAreaId(long area_id);
	
	/** 【房源征集】 根据区域及其征集数
	 * 	注：目前只有上海市，且只取第二级区域【area_id为上海市ID，目前为792】
	 * **/
	public List<CollectionCountByAreaVO> getAreaCollectionByAreaId(long area_id);
	
	/** 【房源征集】 个人中心获取最新10条征集**/
	public List<Collection> getLatestCollection();
	
	/** 【房源征集】 个人中心获取今日征集数**/
	public int getTodayCollectionCount(int area_id);
	
	/** 【从房源供求中进入查看房源时】更新提交的房源回答次数**/
	public boolean updateReadTime(ResponseCollectionGto responseCollectionGto);
	
	/** 【向征集问题添加回答房源】时，判断添加的房源是否已经存在 **/
	public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto);
	
	/** 获取我发布的房源的数量 **/
    public int getMyReleaseCount(long member_id);
	
    /** 2015年10月1日 masw 删除我的征集信息 **/
    public boolean closeMyCollectionById(long collection_id);
    
    /**【2015年10月4日获取区域及其征集数，新方法 BY YANGFENG】**/
    public List<CollectionCountByAreaVO> getCollectionCountByAreaIdV2(long area_id);
}
