package com.yongjinbao.collection.vo;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.houseinfo.entity.HouseInfo;

public class MyResponseHouseInfoVO extends HouseInfo{
	
	private static final long serialVersionUID = 6126879113018175002L;
	
	private String fullName;

	/** 【我提交的数据】  房源从征集中心被查看的次数**/
	private int response_readTime;
	
	/** 【我提交的数据】  查看或未查看**/
	private boolean response_readStatus;

	public int getResponse_readTime() {
		return response_readTime;
	}

	public void setResponse_readTime(int response_readTime) {
		this.response_readTime = response_readTime;
	}

	public boolean isResponse_readStatus() {
		return response_readStatus;
	}

	public void setResponse_readStatus(boolean response_readStatus) {
		this.response_readStatus = response_readStatus;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
