package com.yongjinbao.member.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dao.IBrowseFavoriteInfoDao;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.CustomerHouseStyle;
import com.yongjinbao.member.service.IBrowseFavoriteCustomerInfoService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class BrowseFavoriteCustomerInfoServiceImpl extends BaseServiceImpl<BrowseFavoriteInfo,Integer> implements IBrowseFavoriteCustomerInfoService{
	
    @Inject
    private IBrowseFavoriteInfoDao browseFavoriteInfoDao;
    
    @Inject
    public void setBaseDao(IBrowseFavoriteInfoDao browseFavoriteInfoDao) {
        super.setBaseDao(browseFavoriteInfoDao);
    }

	@Override
	public void addBrowseCustomerInfo(BrowseFavoriteInfo browseFavoriteInfo) {
		browseFavoriteInfo.setBrowseFavoriteStyle(BrowseFavoriteStyle.Browse);
		browseFavoriteInfo.setCustomerHouseStyle(CustomerHouseStyle.CustomerInfo);
		browseFavoriteInfoDao.add(browseFavoriteInfo);
	}

	@Override
	public void addFavoriteCustomerInfo(BrowseFavoriteInfo browseFavoriteInfo) {
		// TODO Auto-generated method stub
		browseFavoriteInfo.setBrowseFavoriteStyle(BrowseFavoriteStyle.Favorites);
		browseFavoriteInfo.setCustomerHouseStyle(CustomerHouseStyle.CustomerInfo);
		browseFavoriteInfoDao.add(browseFavoriteInfo);
	}

	@Override
	public void cancelBrowseFavoriteInfo(int id) {
		browseFavoriteInfoDao.cancelBrowseFavoriteInfo(id);
	}

	@Override
	public Pager<HouseInfo> getBrowseCustomerInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto) {
		getBrowseFavoriteHouseInfoListDto.setBrowseFavoriteStyle(BrowseFavoriteStyle.Browse);
		getBrowseFavoriteHouseInfoListDto.setCustomerHouseStyle(CustomerHouseStyle.CustomerInfo);
//		Pager<HouseInfoNew> browseFavoriteHouseInfoList = browseFavoriteInfoDao.getBrowseFavoriteHouseInfoList(getBrowseFavoriteHouseInfoListDto);
		return null;
	}

	@Override
	public Pager<HouseInfo> getFavoriteCustomerInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto) {
		getBrowseFavoriteHouseInfoListDto.setBrowseFavoriteStyle(BrowseFavoriteStyle.Favorites);
		getBrowseFavoriteHouseInfoListDto.setCustomerHouseStyle(CustomerHouseStyle.CustomerInfo);
		
//		Pager<HouseInfoNew> browseFavoriteHouseInfoList = browseFavoriteInfoDao.getBrowseFavoriteHouseInfoList(getBrowseFavoriteHouseInfoListDto);
		return null;
	}
    


}
