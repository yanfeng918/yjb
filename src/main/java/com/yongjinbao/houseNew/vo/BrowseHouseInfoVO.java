package com.yongjinbao.houseNew.vo;

import com.yongjinbao.houseNew.entity.HouseInfoNew;

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
	private HouseInfoNew houseInfoNew;

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

	public HouseInfoNew getHouseInfoNew() {
		return houseInfoNew;
	}

	public void setHouseInfoNew(HouseInfoNew houseInfoNew) {
		this.houseInfoNew = houseInfoNew;
	}

	@Override
	public String toString() {
		return "BrowseHouseInfoVO [isBought=" + isBought + ", isBalanceEnough=" + isBalanceEnough + ", houseInfoNew="
				+ houseInfoNew + "]";
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	
	
	
}
