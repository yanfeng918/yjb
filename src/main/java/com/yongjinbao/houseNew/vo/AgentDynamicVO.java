package com.yongjinbao.houseNew.vo;

import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.houseNew.entity.HouseInfoNew;

public class AgentDynamicVO {
	
	private boolean withdrawOrNot;
	
	private WithDraw withDraw;
	
	private HouseInfoNew houseInfoNew;

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

	public HouseInfoNew getHouseInfoNew() {
		return houseInfoNew;
	}

	public void setHouseInfoNew(HouseInfoNew houseInfoNew) {
		this.houseInfoNew = houseInfoNew;
	}
	
}
