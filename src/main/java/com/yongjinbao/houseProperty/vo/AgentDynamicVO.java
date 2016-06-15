package com.yongjinbao.houseProperty.vo;

import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.houseProperty.entity.HouseInfoProperty;

public class AgentDynamicVO {
	
	private boolean withdrawOrNot;
	
	private WithDraw withDraw;
	
	private HouseInfoProperty houseInfoProperty;

	public boolean isWithdrawOrNot() {
		return withdrawOrNot;
	}

	public void setWithdrawOrNot(boolean withdrawOrNot) {
		this.withdrawOrNot = withdrawOrNot;
	}

	public WithDraw getWithDraw() {
		return withDraw;
	}

	public void setWithDraw(WithDraw withDraw) {
		this.withDraw = withDraw;
	}

	public HouseInfoProperty getHouseInfoProperty() {
		return houseInfoProperty;
	}

	public void setHouseInfoProperty(HouseInfoProperty houseInfoProperty) {
		this.houseInfoProperty = houseInfoProperty;
	}
	
}
