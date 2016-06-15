package com.yongjinbao.houseNew.vo;

import com.yongjinbao.houseNew.entity.HouseInfoNew;

public class HouseInfoNewAndFavouriteStatusVO extends HouseInfoNew {
	
	private static final long serialVersionUID = -7448822819742193188L;
	
	/** 【房源数据库】房源信息是否已收藏 **/
	private Boolean favouriteStatus;
	
	/** 【区域FULLNAME】**/
	private String area_fullName;


	
	public Boolean getFavouriteStatus() {
		return favouriteStatus;
	}

	public void setFavouriteStatus(Boolean favouriteStatus) {
		this.favouriteStatus = favouriteStatus;
	}


	public String getArea_fullName() {
		return area_fullName;
	}

	public void setArea_fullName(String area_fullName) {
		this.area_fullName = area_fullName;
	}

}
