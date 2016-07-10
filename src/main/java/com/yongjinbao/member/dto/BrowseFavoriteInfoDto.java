package com.yongjinbao.member.dto;

import com.yongjinbao.commons.dto.BaseDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;

public class BrowseFavoriteInfoDto extends BaseDto{
	
	private int houseInfo_id;
	private int customer_id;
	/**
	 * 客源、房源信息类型
	 */
	public BrowseFavoriteInfo.HouseStyle houseStyle;

}
