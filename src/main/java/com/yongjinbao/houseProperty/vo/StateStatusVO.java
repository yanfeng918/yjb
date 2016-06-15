package com.yongjinbao.houseProperty.vo;

public class StateStatusVO {

	/** 【申诉房源】 可否被申诉 **/
	private boolean canBeStated;
	
	/** 【申诉报房源】 不能被申诉时的原因 **/
	private String statusFailReason;
	
	private String stateContent;
	
	private long houseInfo_id;
	
	private String currentStatus;

	public boolean isCanBeStated() {
		return canBeStated;
	}
	
	public void setCanBeStated(boolean canBeStated) {
		this.canBeStated = canBeStated;
	}

	public String getStatusFailReason() {
		return statusFailReason;
	}

	public void setStatusFailReason(String statusFailReason) {
		this.statusFailReason = statusFailReason;
	}

	public void setHouseInfo_id(long houseInfo_id){
		this.houseInfo_id=houseInfo_id;
	}
	
	public long getHouseInfo_id() {
		return houseInfo_id;
	}

	public String getStateContent() {
		return stateContent;
	}

	public void setStateContent(String stateContent) {
		this.stateContent = stateContent;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	
}
