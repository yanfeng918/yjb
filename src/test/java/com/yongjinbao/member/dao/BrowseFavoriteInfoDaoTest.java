package com.yongjinbao.member.dao;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.CustomerHouseStyle;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class BrowseFavoriteInfoDaoTest {
	
	@Inject
	private IBrowseFavoriteInfoDao browseFavoriteInfoDao;
	
	@Inject
	private IMemberService memberService;

	@Test
	public void testAddBrowseFavoriteInfo() {
		Member m = new Member();
		m.setPassword("123");
		Member load = memberService.load(1l);
		BrowseFavoriteInfo b = new BrowseFavoriteInfo();
		b.setBrowseFavoriteStyle(BrowseFavoriteInfo.BrowseFavoriteStyle.Favorites);
		b.setCustomerHouseStyle(CustomerHouseStyle.CustomerInfo);
		b.setCreateDate(new Date());
		b.setMember(load);
		browseFavoriteInfoDao.add(b);
	}
	
	@Test
	public void testDeleteBrowseFavoriteInfo() {
		browseFavoriteInfoDao.delete(1);
	}
	
	
	@Test
	public void testGetBrowseFavoriteHouseInfoList() {
		
		Member load = memberService.load(2l);
		GetBrowseFavoriteInfoListDto a= new GetBrowseFavoriteInfoListDto();
		a.setBrowseFavoriteStyle(BrowseFavoriteStyle.Favorites);
		a.setCustomerHouseStyle(CustomerHouseStyle.HouseInfo);
		a.setMember_id(2);
		SystemContext.setPageSize(10);
		SystemContext.setPageNumber(1);
		SystemContext.setPageOffset(0);
//		Pager<HouseInfoValid> pager = browseFavoriteInfoDao.getBrowseFavoriteHouseInfoList(a);
//		System.out.println(pager.getList().size());
	}

}
