package com.yongjinbao.collection.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.collection.dao.ICollectionDao;
import com.yongjinbao.collection.dto.GetCollectionDto;
import com.yongjinbao.collection.dto.GetMyResponseDto;
import com.yongjinbao.collection.dto.ResponseCollectionGto;
import com.yongjinbao.collection.entity.Collection;
import com.yongjinbao.collection.vo.CollectionCountByAreaVO;
import com.yongjinbao.collection.vo.MyResponseHouseInfoVO;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

@Repository
public class CollectionDaoImpl extends BaseDaoImpl<Collection, Long>implements ICollectionDao {

	@Override
	public boolean addCollection(Collection collection) {
		int insert = getSqlSession().insert(Collection.class.getName()+".addCollection", collection);
		return insert>0;
	}

	@Override
	public Collection load(Long collectionId) {
		Collection collection = getSqlSession().selectOne(Collection.class.getName()+".load",
				collectionId);
		return collection;
	}

	@Override
	public List<Collection> getAllCollectionCdtList(GetCollectionDto getCollectionDto) {
		return getSqlSession().selectList(Collection.class.getName()+".getCollectionCdtList", getCollectionDto);
	}

	@Override
	public List<Collection> getAllCollectionLikeList(GetCollectionDto getCollectionDto) {
		return getSqlSession().selectList(Collection.class.getName()+".getCollectionLikeList", getCollectionDto);
	}

	@Override
	public int getAllCollectionCdtListCount(GetCollectionDto getCollectionDto) {
		return getSqlSession().selectOne(Collection.class.getName()+".getCollectionCdtListCount", getCollectionDto);
	}

	@Override
	public int getAllCollectionLikeListCount(GetCollectionDto getCollectionDto) {
		return getSqlSession().selectOne(Collection.class.getName()+".getCollectionLikeListCount", getCollectionDto);
	}

	@Override
	public List<Collection> getMyCollectionList(GetCollectionDto getCollectionDto) {
		return getSqlSession().selectList(Collection.class.getName()+".getMyCollectionList", getCollectionDto);
	}

	@Override
	public int getMyCollectionListCount(GetCollectionDto getCollectionDto) {
		return getSqlSession().selectOne(Collection.class.getName()+".getMyCollectionListCount", getCollectionDto);
	}

	@Override
	public int getResponseCountByCollectId(GetMyResponseDto myResponseDto) {
		return getSqlSession().selectOne(Collection.class.getName()+".getResponseCountByCollectId", myResponseDto);
	}

	@Override
	public List<HouseInfo> getResponseHouseInfoByCollectId(GetMyResponseDto myResponseDto) {
		List<HouseInfo> list = new ArrayList<HouseInfo>();
		list = getSqlSession().selectList(Collection.class.getName()+".getResponseHouseInfoByCollectId", myResponseDto);
		return list;
	}

	@Override
	public boolean addResponseToCollection(ResponseCollectionGto responseCollectionGto) {
		responseCollectionGto.setHouseInfo_id(getResponseHouseInfoId(responseCollectionGto.getResponse_member_id()));
		int addResponse = getSqlSession().insert(Collection.class.getName()+".addResponseToCollection",
				responseCollectionGto);
		return addResponse>0;
	}

	private long getResponseHouseInfoId(long member_id){
		return getSqlSession().selectOne(Collection.class.getName()+".getCurrentResponseHouseInfoId", member_id);
	}

	@Override
	public List<MyResponseHouseInfoVO> getMyResponsedHouseInfoList(GetMyResponseDto myResponseDto) {
		return getSqlSession().selectList(Collection.class.getName()+".getMyResponsedHouseInfoList", myResponseDto);
	}

	@Override
	public int getMyResponsedHouseInfoListCount(GetMyResponseDto myResponseDto) {
		return getSqlSession().selectOne(Collection.class.getName()+".getMyResponsedHouseInfoListCount", myResponseDto);
	}

	@Override
	public int getCollectionCountByAreaId(long area_id) {
		String area_idString = ","+String.valueOf(area_id)+",";
		return getSqlSession().selectOne(Collection.class.getName()+".getCollectionCountByAreaId", area_idString);
	}

	@Override
	public List<Collection> getLatestCollection() {
		return getSqlSession().selectList(Collection.class.getName()+".getLatestCollection");
	}

	@Override
	public int getTodayCollectionCount(int area_id) {
		return getSqlSession().selectOne(Collection.class.getName()+".getTodayCollectionCount", area_id);
	}

	@Override
	public boolean updateReadTime(ResponseCollectionGto responseCollectionGto) {
		int update = getSqlSession().update(Collection.class.getName()+".updateReadTime", responseCollectionGto);
		return update>0;
	}

	@Override
	public int getMyReleaseCount(long member_id) {
		int count = getSqlSession().selectOne(Collection.class.getName()+".getMyReleaseCount", member_id);
		return count;
	}

//	@Override
//	public boolean delMyCollection(long collection_id) {
//		int del = getSqlSession().delete(Collection.class.getName()+".deleteMyCollectionById", collection_id);
//		return del>0;
//	}
//
//	@Override
//	public void delMyCollectionResponse(long collection_id) {
//		getSqlSession().delete(Collection.class.getName()+".deleteMyCollectionResponseById", collection_id);
//	}

	@Override
	public List<CollectionCountByAreaVO> getCollectionCountByAreaIdV2(long area_id) {
		List<CollectionCountByAreaVO> selectList = getSqlSession().selectList(Collection.class.getName()+".getCollectionCountByAreaIdV2",area_id);
		return selectList;
	}
	
	@Override
	public boolean closeMyCollection(long collection_id) {
		int update = getSqlSession().delete(Collection.class.getName()+".closeMyCollectionById", collection_id);
		return update>0;
	}
}
