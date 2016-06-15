package com.yongjinbao.finance.vo;

public class IncomeAmountVO {
	
	/** 【昨日收益】 **/
	private Float yesterdaySum;
	
	/** 【总共收益】 **/
//	private float totalSum;
	
	/** 【账户余额】 **/
	private float balance;

	public float getYesterdaySum() {
		return yesterdaySum;
	}

	public void setYesterdaySum(float yesterdaySum) {
		this.yesterdaySum = yesterdaySum;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	//2015年10月6日修改 返回账户余额
	
	

//	public float getTotalSum() {
//		return totalSum;
//	}
//
//	public void setTotalSum(float totalSum) {
//		this.totalSum = totalSum;
//	}
	
	

}
