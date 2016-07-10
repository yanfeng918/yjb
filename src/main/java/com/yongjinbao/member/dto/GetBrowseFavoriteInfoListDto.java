package com.yongjinbao.member.dto;

import com.yongjinbao.commons.dto.BaseDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;

/**
 * 获取 浏览 收藏(房源)信息的Dto
 * @author yanfeng
 *
 */
public class GetBrowseFavoriteInfoListDto extends BaseDto{
	
	/**
	 * 浏览还是收藏类型
	 */
	private BrowseFavoriteStyle browseFavoriteStyle;
	/**
	 * 房源还是客源类型
	 */
	private BrowseFavoriteInfo.HouseStyle houseStyle;

	public BrowseFavoriteStyle getBrowseFavoriteStyle() {
		return browseFavoriteStyle;
	}

	public void setBrowseFavoriteStyle(BrowseFavoriteStyle browseFavoriteStyle) {
		this.browseFavoriteStyle = browseFavoriteStyle;
	}

	public BrowseFavoriteInfo.HouseStyle getHouseStyle() {
		return houseStyle;
	}

	public void setHouseStyle(BrowseFavoriteInfo.HouseStyle houseStyle) {
		this.houseStyle = houseStyle;
	}
}
