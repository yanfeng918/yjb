package com.yongjinbao.collection.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.collection.dao.ICollectionDao;
import com.yongjinbao.collection.dto.AddCollectionHouseInfoDto;
import com.yongjinbao.collection.dto.GetCollectionDto;
import com.yongjinbao.collection.dto.GetMyResponseDto;
import com.yongjinbao.collection.dto.ResponseCollectionGto;
import com.yongjinbao.collection.entity.Collection;
import com.yongjinbao.collection.service.ICollectionService;
import com.yongjinbao.collection.vo.CollectionCountByAreaVO;
import com.yongjinbao.collection.vo.CollectionInfoVO;
import com.yongjinbao.collection.vo.MyResponseHouseInfoVO;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.houseinfo.dto.HouseInfoExistDto;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.service.impl.HouseInfoService;
import com.yongjinbao.houseinfo.vo.HouseInfoAndFavouriteStatusVO;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;

@Service
public class CollectionService extends BaseServiceImpl<Collection, Long>implements ICollectionService {

	@Inject
	private ICollectionDao collectionDao;
	
	@Inject
	private IHouseInfoService houseInfoService;
	
	@Inject
	private IAreaService areaService;
	
	@Inject
    public void setBaseDao(ICollectionDao collectionDao) {
        super.setBaseDao(collectionDao);
    }
	
	@Override
	public boolean addCollection(Collection collection) {
		return collectionDao.addCollection(collection);
	}

	@Override
	public <T>Pager<T> getAllCollectionList(GetCollectionDto getCollectionDto) {
		if (getCollectionDto.getCommunity()!=null) {
			getCollectionDto.setCommunity(getCollectionDto.getCommunity().trim());
		}
		getCollectionDto.setPageOffset((getCollectionDto.getPageNumber()-1)*getCollectionDto.getPageSize());
		Pager<Collection> pages = new Pager<Collection>();
		int total=0;
		List<Collection> list = new ArrayList<Collection>();
		int area_id = getCollectionDto.getArea_id();
		List<Area> findChildren = areaService.findChildren(area_id);
		if(findChildren.isEmpty()){
			list = collectionDao.getAllCollectionCdtList(getCollectionDto);
			total=collectionDao.getAllCollectionCdtListCount(getCollectionDto);
		}else{
			list = collectionDao.getAllCollectionLikeList(getCollectionDto);
			total=collectionDao.getAllCollectionLikeListCount(getCollectionDto);
		}
		pages.setList(list);
		pages.setTotalCount(total);        
		pages.setPageOffset(getCollectionDto.getPageOffset());
		pages.setPageSize(getCollectionDto.getPageSize());
		return (Pager<T>)pages;
	}

	@Override
	public Collection load(long id) {
		return collectionDao.load(id);
	}

	@Override
	public boolean addResponseToCollection(AddCollectionHouseInfoDto addCollectionHouseInfoGto) {
		//生成一条房源信息
		HouseInfo houseInfo = addCollectionHouseInfoGto.getHouseInfo();
		houseInfoService.add(houseInfo);
		//写入关联表
		ResponseCollectionGto responseCollectionGto = new ResponseCollectionGto();
		responseCollectionGto.setReadTime(0);
		responseCollectionGto.setReadStatus(false);
		responseCollectionGto.setResponse_member_id(houseInfo.getMember().getId());
		responseCollectionGto.setCollection_id(addCollectionHouseInfoGto.getCollection_id());
//		responseCollectionGto.setHouseInfo_id(houseInfo_id);//houseInfo_id在DAO层添加
		return collectionDao.addResponseToCollection(responseCollectionGto);
	}
	@Override
	public int getResponseCountByCollectId(GetMyResponseDto myResponseDto) {
		return collectionDao.getResponseCountByCollectId(myResponseDto);
	}
	
	@Override
	public Pager<Collection> getMyCollectionList(GetCollectionDto getCollectionDto) {
		if(getCollectionDto.getPageSize()==0){
			getCollectionDto.setPageSize(10);
		}
		if(getCollectionDto.getPageNumber()==0){
			getCollectionDto.setPageNumber(1);
		}
		getCollectionDto.setPageOffset((getCollectionDto.getPageNumber()-1)
                *getCollectionDto.getPageSize());
        Pager<Collection> pager=new Pager<Collection>();
        pager.setPageOffset(getCollectionDto.getPageOffset());
        pager.setPageSize(getCollectionDto.getPageSize());
        List<Collection> list=collectionDao.getMyCollectionList(getCollectionDto);
        int total=collectionDao.getMyCollectionListCount(getCollectionDto);
        pager.setList(list);
        pager.setTotalCount(total);
        return pager;
	}
	
	@Override
	public int getCollectionCountByAreaId(long area_id) {
		return collectionDao.getCollectionCountByAreaId(area_id);
	}
	
	@Override
	public List<CollectionCountByAreaVO> getAreaCollectionByAreaId(long area_id) {
		//本方法只针对上海市及其二级子区域
		List<Area> areas = new ArrayList<Area>();//上海市的二级子区域
		Area root = areaService.load(area_id);//上海市
		if (root != null) {
			areas = new ArrayList<Area>(areaService.findChildren(area_id));
		}
		List<CollectionCountByAreaVO> voList = new ArrayList<CollectionCountByAreaVO>();
		CollectionCountByAreaVO collectionCountByAreaVO = null;
		for (Area area : areas) {
			collectionCountByAreaVO = new CollectionCountByAreaVO();
			collectionCountByAreaVO.setAreaId(area.getId());
			collectionCountByAreaVO.setAreaName(area.getName());
			collectionCountByAreaVO.setCollectionCount(getCollectionCountByAreaId(area.getId()));
			voList.add(collectionCountByAreaVO);
		}
		return voList;
	}

	@Override
	public Pager<HouseInfo> getResponseHouseInfoByCollectId(GetMyResponseDto myResponseDto) {
		myResponseDto.setPageOffset((myResponseDto.getPageNumber()-1)
                *myResponseDto.getPageSize());
        Pager<HouseInfo> pages=new Pager<HouseInfo>();
        List<HouseInfo> list=collectionDao.getResponseHouseInfoByCollectId(myResponseDto);
        for (HouseInfo houseInfo : list) {
        	houseInfo.setMobile(houseInfo.getMobile().replace(houseInfo.getMobile().substring(3, 7), "****"));
		}
        int total=collectionDao.getResponseCountByCollectId(myResponseDto);
        pages.setList(list);
        pages.setTotalCount(total);
        pages.setPageOffset(myResponseDto.getPageOffset());
        pages.setPageSize(myResponseDto.getPageSize());
        return pages;
	}

	@Override
	public Pager<MyResponseHouseInfoVO> getMyResponsedHouseInfoList(GetMyResponseDto myResponseDto) {
		myResponseDto.setPageOffset((myResponseDto.getPageNumber()-1)
                *myResponseDto.getPageSize());
        Pager<MyResponseHouseInfoVO> pages=new Pager<MyResponseHouseInfoVO>();
        List<MyResponseHouseInfoVO> list=collectionDao.getMyResponsedHouseInfoList(myResponseDto);
        int total=collectionDao.getMyResponsedHouseInfoListCount(myResponseDto);
        pages.setList(list);
        pages.setTotalCount(total);
        pages.setPageOffset(myResponseDto.getPageOffset());
        pages.setPageSize(myResponseDto.getPageSize());
        return pages;
	}

	@Override
	public List<Collection> getLatestCollection() {
		return collectionDao.getLatestCollection();
	}

	@Override
	public int getTodayCollectionCount(int area_id) {
		return collectionDao.getTodayCollectionCount(area_id);
	}
	
	@Override
	public boolean updateReadTime(ResponseCollectionGto responseCollectionGto) {
		return collectionDao.updateReadTime(responseCollectionGto);
	}
	
	@Override
	public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto) {
//		if (!houseInfoService.isHouseInfoExist(houseInfoExistDto)) {
//			boolean isRemoteExisted = false;
//			try {
//				isRemoteExisted = houseInfoService.isHouseInfoExistRemoteInvacation(houseInfoExistDto);
//				return isRemoteExisted;
//			} catch (Exception e) {
//				return false;
//			}
//		}else {
//			return true;
//		}
		HouseInfo houseInfo = new HouseInfo();
		houseInfo.setMobile(houseInfoExistDto.getMobile());
		return houseInfoService.validateHouseInfo(houseInfo);
	}
	
	@Override
	public int getMyReleaseCount(long member_id) {
		int myReleaseCount = collectionDao.getMyReleaseCount(member_id);
		return myReleaseCount;
	}
	
	@Override
	public boolean closeMyCollectionById(long collection_id) {
		try {
			//关闭征集
			return collectionDao.closeMyCollection(collection_id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<CollectionCountByAreaVO> getCollectionCountByAreaIdV2(long area_id) {
		
		List<CollectionCountByAreaVO> list = new ArrayList<CollectionCountByAreaVO>();
		String Key = "getCollectionCountByAreaIdV2"+area_id;
		int expired_Time=300;
        byte[] res = RedisUtils.get(SerializeUtil.serialize(Key));
        if (res == null ) {
        	list = collectionDao.getCollectionCountByAreaIdV2(area_id);
        	byte[] serialize = SerializeUtil.serialize(list);
			RedisUtils.set(SerializeUtil.serialize(Key), serialize,expired_Time);
		}else {
			list = (List<CollectionCountByAreaVO>)SerializeUtil.unserialize(res);
		}
		return list;
	}
}
