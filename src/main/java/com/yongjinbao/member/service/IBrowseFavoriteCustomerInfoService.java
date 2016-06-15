package com.yongjinbao.member.service;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by yanfeng on 2015/8/20
 */
public interface IBrowseFavoriteCustomerInfoService extends IBaseService<BrowseFavoriteInfo,Integer>{
	
	/**
	 * 新增 【浏览 客源】信息
	 * @param browseFavoriteInfo
	 */
	public void addBrowseCustomerInfo(BrowseFavoriteInfo browseFavoriteInfo);
	
	/**
	 * 新增 【浏览 客源】信息
	 * @param browseFavoriteInfo
	 */
	public void addFavoriteCustomerInfo(BrowseFavoriteInfo browseFavoriteInfo);
	
	/**
	 * 取消 浏览 收藏信息
	 * @param id
	 */
	public void cancelBrowseFavoriteInfo(int id);
	
	/**
	 * 获取 (浏览 )、(客源)信息
	 * @param getBrowseFavoriteHouseInfoListDto
	 */
	public Pager<HouseInfo> getBrowseCustomerInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto);
	
	/**
	 * 获取 (收藏)、(客源)信息
	 * @param getBrowseFavoriteHouseInfoListDto
	 */
	public Pager<HouseInfo> getFavoriteCustomerInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto);
	
}
