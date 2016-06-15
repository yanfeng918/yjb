package com.yongjinbao.houseProperty.vo;

import com.yongjinbao.houseProperty.entity.HouseInfoProperty;

public class UpdateHouseInfoVO {
	
	// **************************************
	// 【修改房源信息】
	// 	审核通过的系统房源不能被修改
	//	审核通过的自定义房源只能修改价格信息
	//	审核中或不通过的系统房源或自定义房源，信息全部可修改！
	// **************************************
	
	/** 是否能被修改*/
	private boolean isUpdate;
	
	/** 是否只能修改价格*/
	private boolean onlyPrice;
	
	/** 能够修改时返回houseInfo **/
	private HouseInfoProperty houseInfoProperty;

    public boolean isOnlyPrice() {
		return onlyPrice;
	}

	public void setOnlyPrice(boolean onlyPrice) {
		this.onlyPrice = onlyPrice;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public HouseInfoProperty getHouseInfoProperty() {
		return houseInfoProperty;
	}

	public void setHouseInfoProperty(HouseInfoProperty houseInfoProperty) {
		this.houseInfoProperty = houseInfoProperty;
	}
}
