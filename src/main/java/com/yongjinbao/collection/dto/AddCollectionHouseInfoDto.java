package com.yongjinbao.collection.dto;

import com.yongjinbao.houseinfo.entity.HouseInfo;

public class AddCollectionHouseInfoDto {
	
	/** 【提交数据】 征集id**/
	private long collection_id;
	
	/** 【提交数据】 应征房源**/
	private HouseInfo houseInfo;

	public long getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(long collection_id) {
		this.collection_id = collection_id;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}
	
}
