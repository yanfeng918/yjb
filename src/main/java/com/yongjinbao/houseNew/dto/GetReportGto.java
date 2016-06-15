package com.yongjinbao.houseNew.dto;

/**
 * Created by mashenwei on 2015/9/8.
 */
public class GetReportGto {
	
	/** 【举报信息】房源id **/
	private long houseInfo_id;
	
	/** 【举报信息】举报者id **/
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
