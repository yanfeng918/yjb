package com.yongjinbao.member.dto;

public class MyBrowseInfoDto {
	
	public static final String Browse = "Browse", Favorites = "Favorites";
	
	/**
	 * 【我的查看房源】关联房源信息ID
	 */
	private long houseInfo_id;
	
	/**
	 * 【我的查看房源】关联会员ID
	 */
	private long member_id;
	
	/**
	 * 【我的查看房源】---browser;
	 */
	private String browseFavoriteStyle;
	
	private String houseStyle;
	

	public long getHouseInfo_id() {
		return houseInfo_id;
	}

	public void setHouseInfo_id(long houseInfo_id) {
		this.houseInfo_id = houseInfo_id;
	}

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public String getBrowseFavoriteStyle() {
		return browseFavoriteStyle;
	}
	
	public void setBrowseFavoriteStyle(String browseFavoriteStyle) {
		this.browseFavoriteStyle = browseFavoriteStyle;
	}

	public String getHouseStyle() {
		return houseStyle;
	}

	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}
}
