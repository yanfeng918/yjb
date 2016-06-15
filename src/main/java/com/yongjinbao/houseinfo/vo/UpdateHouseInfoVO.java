package com.yongjinbao.houseinfo.vo;

import com.yongjinbao.houseinfo.entity.HouseInfo;

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
	private HouseInfo houseInfo;

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

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}
}
