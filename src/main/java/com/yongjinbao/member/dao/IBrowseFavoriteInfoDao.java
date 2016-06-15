package com.yongjinbao.member.dao;

import java.util.List;
import java.util.List;

import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.vo.GetMyBrowseVO;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by yanfeng on 2015/8/20.
 */
public interface IBrowseFavoriteInfoDao extends IBaseDao<BrowseFavoriteInfo,Integer> {
	
	/**
	 * 新增 浏览 收藏信息
	 * @param browseFavoriteInfo
	 */
	public void addBrowseFavoriteInfo(BrowseFavoriteInfo browseFavoriteInfo);
	
	/**
	 * 取消 浏览 收藏信息
	 * @param id
	 */
	public void cancelBrowseFavoriteInfo(int id);
	
	/**
	 * 获取 (浏览 收藏)、(房源/客源)信息
	 * @param getBrowseFavoriteHouseInfoListDto
	 */
	public List<GetMyBrowseVO> getBrowseFavoriteHouseInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto);

    /**
     * 获取收藏房源信息列表数量
     * @param getBrowseFavoriteHouseInfoListDto
     * @return
     */
	public int getBrowseFavoriteHouseInfoCount(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto);

	/**
	 * 根据房源ID获取所有查看房源
	 */
	public List<BrowseFavoriteInfo> getBrowseInfoByHouseId(MyBrowseInfoDto myBrowseInfoDto);
	
	/**
	 * 获取房源购买至今的时间【单位：分钟】
	 */
	public int getBrowseMinutesByNow(MyBrowseInfoDto myBrowseInfoDto);
}
