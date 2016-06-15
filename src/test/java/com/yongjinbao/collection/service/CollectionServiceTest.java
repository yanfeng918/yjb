package com.yongjinbao.collection.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.collection.dao.ICollectionDao;
import com.yongjinbao.collection.dto.AddCollectionHouseInfoDto;
import com.yongjinbao.collection.dto.GetCollectionDto;
import com.yongjinbao.collection.dto.ResponseCollectionGto;
import com.yongjinbao.collection.entity.Collection;
import com.yongjinbao.collection.vo.CollectionCountByAreaVO;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.entity.Pager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class CollectionServiceTest {
	@Inject
	private ICollectionService collectionService;
	
	@Inject
	private ICollectionDao collectionDao;
	@Test
	public void addCollection(){
		Collection collection =  new Collection();
		collection.setAreaSize(100f);
		collection.setArea_id(3467l);
		collection.setBan("10");
		collection.setCollectPrice(50f);
		collection.setCommunity("共和小区");
		collection.setDescription("");
		collection.setFloor("6");
		collection.setHouseShape("二室一厅");
		collection.setMember_id(66666712l);
		collection.setRoomNumber("601");
		collection.setSalePrice(300f);
		try {
			collectionService.addCollection(collection);
			System.out.println("添加征集信息成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("添加失败！");
		}
	}
	
	
	@Test
	public void load(){
		Collection collection = collectionService.load(1l);
		System.out.println(collection.getCommunity()+"\n");
	}
	
	@Test
	public void addResponse(){
		AddCollectionHouseInfoDto addCollectionHouseInfoGto = new AddCollectionHouseInfoDto();
		addCollectionHouseInfoGto.setCollection_id(3l);
		HouseInfo houseInfo = new HouseInfo();
		Area area = new Area();
		area.setId(3336l);
		Member member = new Member();
		member.setId(66666713l);
		houseInfo.setArea(area);
		houseInfo.setMember(member);
//		houseInfo.setId(182l);
		addCollectionHouseInfoGto.setHouseInfo(houseInfo);
		try {
			collectionService.addResponseToCollection(addCollectionHouseInfoGto);
			System.out.println("回答成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("回答失败!");
		}
		
	}
	
	@Test
	public void getAreaCollectionByAreaId(){
		List<CollectionCountByAreaVO> voList = collectionService.getAreaCollectionByAreaId(792l);
		for (CollectionCountByAreaVO areaVO : voList) {
			System.out.println("************************");
			System.out.println("区域名称==>>"+areaVO.getAreaName());
			System.out.println("征集数==>>"+areaVO.getCollectionCount());
		}
	}
	
	@Test
	public void updateReadTime(){
		ResponseCollectionGto responseCollectionGto = new ResponseCollectionGto();
		responseCollectionGto.setCollection_id(4l);
		responseCollectionGto.setHouseInfo_id(199l);
		try {
			collectionService.updateReadTime(responseCollectionGto);
			System.out.println("成功！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("失败！");
		}
	}
	
	@Test
	public void getMyReleaseCount(){
		int myReleaseCount = collectionService.getMyReleaseCount(66666712l);
		System.out.println(myReleaseCount);
	}
	
}
