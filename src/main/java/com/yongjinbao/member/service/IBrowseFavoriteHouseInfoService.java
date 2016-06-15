package com.yongjinbao.member.service;

import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.vo.GetMyBrowseVO;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by yanfeng on 2015/8/20
 */
public interface IBrowseFavoriteHouseInfoService extends IBaseService<BrowseFavoriteInfo,Integer>{
	
	/**
	 * 新增 【浏览 房源】信息
	 * @param browseFavoriteInfo
	 */
	public void addBrowseHouseInfo(BrowseFavoriteInfo browseFavoriteInfo);
	
	/**
	 * 新增 【收藏 房源】信息
	 * @param browseFavoriteInfo
	 */
	public void addFavoriteHouseInfo(BrowseFavoriteInfo browseFavoriteInfo);
	
	/**
	 * 取消 浏览 收藏信息
	 * @param id
	 */
	public void cancelBrowseFavoriteInfo(int id);
	
	/**
	 * 获取 (浏览 )、(房源)信息
	 * @param getBrowseFavoriteHouseInfoListDto
	 */
	public Pager<GetMyBrowseVO> getBrowseHouseInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto);
	
	/**
	 * 获取 (收藏)、(房源)信息
	 * @param getBrowseFavoriteHouseInfoListDto
	 */
	public Pager<GetMyBrowseVO> getFavoriteHouseInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto);
	
	/**
	 * 房源是否存在我查看的房源中
	 */
	public boolean isHouseInfoInMyBrowse(MyBrowseInfoDto myBrowseInfoDto);
	
	/**
	 * 房源信息是否已经收藏
	 */
	public boolean isHouseInfoInMyFavorite(MyBrowseInfoDto myBrowseInfoDto);
	
	/**
	 * 获取房源数据的收藏/查看信息
	 */
	public BrowseFavoriteInfo getBrowseFavoriteInfo(MyBrowseInfoDto myBrowseInfoDto);
	
	/**
	 * 获取房源购买至今的时间【单位：分钟】
	 */
	public int getBrowseMinutesByNow(MyBrowseInfoDto myBrowseInfoDto);
}
