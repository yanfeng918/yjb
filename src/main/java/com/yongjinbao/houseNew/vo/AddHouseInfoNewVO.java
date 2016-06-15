package com.yongjinbao.houseNew.vo;

import com.yongjinbao.houseNew.entity.HouseInfoNew;

public class AddHouseInfoNewVO extends HouseInfoNew {
	
	private boolean canBeSaved;
	
	public boolean isCanBeSaved() {
		return canBeSaved;
	}

	public void setCanBeSaved(boolean canBeSaved) {
		this.canBeSaved = canBeSaved;
	}
	
	
}
