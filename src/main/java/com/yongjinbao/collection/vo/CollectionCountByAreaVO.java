package com.yongjinbao.collection.vo;

import java.io.Serializable;

public class CollectionCountByAreaVO implements Serializable {
	
	private static final long serialVersionUID = -3722922951924833526L;

	/** 【获取区域及征集数】 区域id**/
	private long areaId;
	
	/** 【获取区域及征集数】 区域名称**/
	private String areaName;
	
	/** 【获取区域及征集数】 征集数**/
	private int collectionCount;
	
	public long getAreaId() {
		return areaId;
	}
	
	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public int getCollectionCount() {
		return collectionCount;
	}
	
	public void setCollectionCount(int collectionCount) {
		this.collectionCount = collectionCount;
	}
	
}
