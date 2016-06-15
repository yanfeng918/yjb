package com.yongjinbao.collection.dto;

public class ResponseCollectionGto {
	
	/** 【我的提交房源】 征集id**/
	private long collection_id;
	
	/** 【我的提交房源】 提交后的房源id**/
	private long houseInfo_id;
	
	/** 【我的提交房源】 提交者member_id**/
	private long response_member_id;
	
	/** 【我的提交房源】 是否查看**/
	private boolean readStatus;
	
	/** 【我的提交房源】 查看次数**/
	private int readTime;

	public long getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(long collection_id) {
		this.collection_id = collection_id;
	}

	public long getHouseInfo_id() {
		return houseInfo_id;
	}

	public void setHouseInfo_id(long houseInfo_id) {
		this.houseInfo_id = houseInfo_id;
	}

	public long getResponse_member_id() {
		return response_member_id;
	}

	public void setResponse_member_id(long response_member_id) {
		this.response_member_id = response_member_id;
	}

	public boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(boolean readStatus) {
		this.readStatus = readStatus;
	}

	public int getReadTime() {
		return readTime;
	}

	public void setReadTime(int readTime) {
		this.readTime = readTime;
	}
	

}
