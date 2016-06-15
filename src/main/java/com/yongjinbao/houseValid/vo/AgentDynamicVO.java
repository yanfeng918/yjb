package com.yongjinbao.houseValid.vo;

import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.houseValid.entity.HouseInfoValid;

public class AgentDynamicVO {
	
	private boolean withdrawOrNot;
	
	private WithDraw withDraw;
	
	private HouseInfoValid houseInfoValid;

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

	public HouseInfoValid getHouseInfoValid() {
		return houseInfoValid;
	}

	public void setHouseInfoValid(HouseInfoValid houseInfoValid) {
		this.houseInfoValid = houseInfoValid;
	}
	
}
