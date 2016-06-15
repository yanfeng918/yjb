package com.yongjinbao.houseinfo.vo;

import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.houseinfo.entity.HouseInfo;

public class AgentDynamicVO {
	
	private boolean withdrawOrNot;
	
	private WithDraw withDraw;
	
	private HouseInfo houseInfo;

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

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}
	
}
