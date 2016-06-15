package com.yongjinbao.houseinfo.vo;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.HouseInfo.HouseInfo_STATUS;

public class AddHouseInfoVO extends	HouseInfo{
	
	private boolean canBeSaved;
	
	public boolean isCanBeSaved() {
		return canBeSaved;
	}

	public void setCanBeSaved(boolean canBeSaved) {
		this.canBeSaved = canBeSaved;
	}
	
	
}
