package com.yongjinbao.houseinfo.dto;

/**
 * 每月奖励
 * Created by mashenwei on 2015/11/15.
 */
public class BonusProcessDto {
	
	/** 查询年月 **/
	private int yearMonth;
	
	/** 查询年月【查询该月是否已经获取奖励时使用】 **/
	private String year_month;
	
	/** 查询者ID **/
	private long member_id;
	
	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public int getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(int yearMonth) {
		this.yearMonth = yearMonth;
		int year = yearMonth/100;
		int month = yearMonth % 100;
		String year_month1 = String.valueOf(year)+"-"+String.valueOf(month);
		setYear_month(year_month1);
	}

	public String getYear_month() {
		return year_month;
	}

	public void setYear_month(String year_month) {
		this.year_month = year_month;
	}
	
}
