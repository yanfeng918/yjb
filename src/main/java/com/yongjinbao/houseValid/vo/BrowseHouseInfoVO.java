package com.yongjinbao.houseValid.vo;

import com.yongjinbao.houseValid.entity.HouseInfoValid;

/**
 * 查看数据时返回BrowseHouseInfoVO
 *
 */
public class BrowseHouseInfoVO {

	/** 查看数据是否自己发布的房源 **/
	private boolean isMine;
	
	/** 查看数据是否已经购买 **/
	private boolean isBought;
	
	/** 查看数据未购买时余额是否充足 **/
	private boolean isBalanceEnough;
	
	/** 查看数据时返回的房源信息 **/
	private HouseInfoValid houseInfoValid;

	public boolean isBought() {
		return isBought;
	}

	public void setBought(boolean isBought) {
		this.isBought = isBought;
	}

	public boolean isBalanceEnough() {
		return isBalanceEnough;
	}

	public void setBalanceEnough(boolean isBalanceEnough) {
		this.isBalanceEnough = isBalanceEnough;
	}

	public HouseInfoValid getHouseInfoValid() {
		return houseInfoValid;
	}

	public void setHouseInfoValid(HouseInfoValid houseInfoValid) {
		this.houseInfoValid = houseInfoValid;
	}

	@Override
	public String toString() {
		return "BrowseHouseInfoVO [isBought=" + isBought + ", isBalanceEnough=" + isBalanceEnough + ", houseInfoValid="
				+ houseInfoValid + "]";
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	
	
	
}
