package com.yongjinbao.houseNew.vo;

import java.io.Serializable;

public class GetAreaHouseCountByCityVO implements Serializable {
	
	/** 【获取区域及房源数】 区域id**/
	private long areaId;
	
	/** 【获取区域及房源数】 区域名称**/
	private String areaName;
	
	/** 【获取区域及房源数】 房源数**/
	private int houseInfoCount;
	
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
	
	public int getHouseInfoCount() {
		return houseInfoCount;
	}
	
	public void setHouseInfoCount(int houseInfoCount) {
		this.houseInfoCount = houseInfoCount;
	}
	
}
