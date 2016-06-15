package com.yongjinbao.houseValid.vo;

import com.yongjinbao.houseValid.entity.HouseInfoValid;

public class AddHouseInfoValidVO extends HouseInfoValid {
	
	private boolean canBeSaved;
	
	public boolean isCanBeSaved() {
		return canBeSaved;
	}

	public void setCanBeSaved(boolean canBeSaved) {
		this.canBeSaved = canBeSaved;
	}
	
	
}
