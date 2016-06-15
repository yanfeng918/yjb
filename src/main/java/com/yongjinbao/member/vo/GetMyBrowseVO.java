package com.yongjinbao.member.vo;

import com.yongjinbao.houseinfo.entity.HouseInfo;

public class GetMyBrowseVO extends HouseInfo{
	
	private static final long serialVersionUID = -3099708604708274940L;

//	/** 【我查看的房源】 举报ID**/
//	private Long report_id;

	/** 【我查看的房源】 是否已举报**/
	private Boolean isReported;
	
	/** 【我查看的房源】 是否过举报期**/
	private Boolean isExpired;
	
	/** 【我查看的房源】 举报审核状态**/
	private String checkStatus;
	
	/** 【我查看的房源】 举报审核原因**/
	private String checkContent;
	
//	private Long report_memberId;
//	public Long getReport_id() {
//		return report_id;
//	}

//	public void setReport_id(Long report_id) {
//		this.report_id = report_id;
//		if (report_id!=null && report_id!=0) {
//			setIsReported(true);
//		}else {
//			setIsReported(false);
//		}
//		setIsReported(!(report_id==null));
		
//	}

	public Boolean getIsReported() {
		return isReported;
	}

	public void setIsReported(Boolean isReported) {
		this.isReported = isReported;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

//	public Long getReport_memberId() {
//		return report_memberId;
//	}
//
//	public void setReport_memberId(Long report_memberId) {
//		this.report_memberId = report_memberId;
//	}
	
}
