package com.yongjinbao.houseinfo.vo;

public class ReportStatusVO {

	/** 【举报房源】 是否已经举报 **/
	private boolean isHouseInfoReported;
	
	/** 【举报房源】 举报时间是否过期 **/
	private boolean isReportedExpired;

	public boolean isHouseInfoReported() {
		return isHouseInfoReported;
	}

	public void setHouseInfoReported(boolean isHouseInfoReported) {
		this.isHouseInfoReported = isHouseInfoReported;
	}

	public boolean isReportedExpired() {
		return isReportedExpired;
	}

	public void setReportedExpired(boolean isReportedExpired) {
		this.isReportedExpired = isReportedExpired;
	}
}
