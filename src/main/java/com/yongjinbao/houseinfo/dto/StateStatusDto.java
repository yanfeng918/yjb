package com.yongjinbao.houseinfo.dto;

/**
 * Created by mashenwei on 2015/11/6.
 */
public class StateStatusDto {
	
	/** 【申诉信息】房源id **/
	private long houseInfo_id;
	
	/** 【申诉信息】申诉者id **/
	private long member_id;
	
	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public long getHouseInfo_id() {
		return houseInfo_id;
	}

	public void setHouseInfo_id(long houseInfo_id) {
		this.houseInfo_id = houseInfo_id;
	}
	
}
