package com.yongjinbao.houseProperty.dto;

public class HouseInfoExistDto {
	
	//新增房源时判断是否在本地房源库存在
	//以手机号、楼栋号和房间号为判定标准
	
	/** 【房源是否存在着】 手机号 **/
	private String mobile;
	
	/** 【房源是否存在着】 楼栋号 **/
	private String ban;
	
	/** 【房源是否存在着】 房间号 **/
	private String roomNumber;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
}
