package com.yongjinbao.houseInfo.dao;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.houseinfo.dao.IHouseInfoDao;
import com.yongjinbao.houseinfo.dto.GetHouseInfoDto;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.entity.SystemContext;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class HouseInfoTest {
    @Inject
    private IHouseInfoDao houseInfoDao;
    @Inject
    private IHouseInfoService houseInfoService;


    @Test
    public void getHouseInfoPage(){
        GetHouseInfoDto getHouseInfoDto=new GetHouseInfoDto();
        SystemContext.setPageSize(10);
        SystemContext.setPageOffset(3);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
//        Pager<HouseInfoNew> pager= houseInfoService.getHouseInfoNew(getHouseInfoDto);
    }
    @Test
    public void add(){
        HouseInfo houseInfo=new HouseInfo();
        Member member=new Member();
        member.setId(Long.parseLong("1"));
        houseInfo.setMember(member);
        houseInfo.setAvailable(true);
        Area area=new Area();
        area.setId(Long.parseLong("1"));
        houseInfo.setArea(area);
        houseInfo.setAreaSize(100);
        houseInfo.setBan("32");
        houseInfo.setCommunity("上海");
        houseInfo.setFloor("32");
        houseInfo.setPhone("233333");
        houseInfo.setMobile("2323");
        houseInfo.setName("xiaohui");
        houseInfo.setSalePrice(12);
        houseInfo.setInfoPrice(32);
        //houseInfo.setStatus(HouseInfoNew.HouseInfo_STATUS.CHECKING);
        houseInfo.setReadTime(2);
        houseInfo.setRoomNumber("sd");
//        houseInfo.setHouseDespt("23");
        houseInfo.setSaleWay(HouseInfo.HouseInfo_SaleWay.CUSTOMER);

        houseInfoDao.add(houseInfo);
    }
    @Test
    public void update(){
        HouseInfo houseInfo=new HouseInfo();
        Member member=new Member();
        Area area=new Area();
        houseInfo.setMember(member);
        houseInfo.setArea(area);
        houseInfo.setId(Long.parseLong("2"));
        houseInfo.setCommunity("中国香港");
        houseInfoDao.update(houseInfo);
    }
    
    @Test
    public void rr(){
//    	GetHouseInfoDto getHouseInfoDto=new GetHouseInfoDto();
//    	getHouseInfoDto.setArea_id(792);
//        List<HouseInfoNew> list=houseInfoDao.getHouseInfoLike(getHouseInfoDto);
//        int count =houseInfoDao.getHouseInfoLikeCount(getHouseInfoDto);
//        for(HouseInfoNew houseInfo:list)
//            System.out.println(houseInfo.getCommunity());
//        System.out.println(count);
    }
    
    @Test
    public void rr1(){
    	GetHouseInfoDto getHouseInfoDto=new GetHouseInfoDto();
    	getHouseInfoDto.setArea_id(793);
    	getHouseInfoDto.setMaxSalePrice(100);
//        List<HouseInfoNew> list=houseInfoDao.getHouseInfoCdt(getHouseInfoDto);
//        
//        int count =houseInfoDao.getHouseInfoCdtCount(getHouseInfoDto);
//        for(HouseInfoNew houseInfo:list){
////            System.out.println(houseInfo.getCommunity());
//            System.out.println(houseInfo.getArea().getFullName());
//        }
//        System.out.println(count);
    }
    
    @Test
    public void buyBrowseInfo(){
    	
    }
}
