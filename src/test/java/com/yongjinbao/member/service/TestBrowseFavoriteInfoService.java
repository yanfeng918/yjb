package com.yongjinbao.member.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.CustomerHouseStyle;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class TestBrowseFavoriteInfoService {
	
	@Inject
	private IBrowseFavoriteCustomerInfoService BrowseFavoriteInfoService;
	
	@Inject
	private IBrowseFavoriteHouseInfoService browseHouseInfoService;

	@Test
	public void testGetBrowseCustomerInfoList() {
		
		SystemContext.setPageSize(10);
		SystemContext.setPageNumber(1);
		SystemContext.setPageOffset(0);
		GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto = new GetBrowseFavoriteInfoListDto();

		Pager<HouseInfo> browseCustomerInfoList = BrowseFavoriteInfoService.getBrowseCustomerInfoList(getBrowseFavoriteHouseInfoListDto);
		
	}
	
	@Test
	public void testGetFavoriteCustomerInfoList() {
		
		SystemContext.setPageSize(10);
		SystemContext.setPageNumber(1);
		SystemContext.setPageOffset(0);
		GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto = new GetBrowseFavoriteInfoListDto();

		Pager<HouseInfo> favoriteCustomerInfoList = BrowseFavoriteInfoService.getFavoriteCustomerInfoList(getBrowseFavoriteHouseInfoListDto);
	}
	
	@Test
	public void getFavoriteHouseInfoList(){
		SystemContext.setPageSize(10);
		SystemContext.setPageNumber(1);
		SystemContext.setPageOffset(0);
		GetBrowseFavoriteInfoListDto dto = new GetBrowseFavoriteInfoListDto();
		dto.setPageNumber(1);
		dto.setPageSize(10);
		dto.setPageOffset(0);
		dto.setCustomerHouseStyle(CustomerHouseStyle.HouseInfo);
		dto.setBrowseFavoriteStyle(BrowseFavoriteStyle.Browse);
		dto.setMember_id(1l);
//		Pager<HouseInfo> pH = browseHouseInfoService.getFavoriteHouseInfoList(dto);
		
		/*for (HouseInfo h : pH.getList()) {
			
		}*/
		
		
	}

}
