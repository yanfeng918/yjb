package com.yongjinbao.houseProperty.vo;

import com.yongjinbao.houseProperty.entity.HouseInfoProperty;

public class AddHouseInfoPropertyVO extends HouseInfoProperty {
	
	private boolean canBeSaved;
	
	public boolean isCanBeSaved() {
		return canBeSaved;
	}

	public void setCanBeSaved(boolean canBeSaved) {
		this.canBeSaved = canBeSaved;
	}
	
	
}
