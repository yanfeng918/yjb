package com.yongjinbao.houseinfo.entity;

import com.yongjinbao.commons.entity.BaseEntity;

/**
 *	Entity--举报信息 
 *	@author mashenwei
 *	@since 2015‎年‎9‎月‎8‎日
 */
public class Report extends BaseEntity {

	private static final long serialVersionUID = 2459571281898585072L;
	
	/**
	 * 举报信息状态 
	 */
	public enum REPORT_STATUS{
		APPLY("待审核",1),CHECKING("审核中",2),SUCCESS("成功",3),FAIL("失败",4);
        private String outername;
        private int value;
        private REPORT_STATUS(String outername,int value){
            this.outername = outername;
            this.value = value;
        }
        public String getOutername() {
            return outername;
        }
        public int getValue() {
            return value;
        }
    }
	/** 举报理由**/
	private String reportReason;
	
//	/** 举报的房源信息**/
//	private HouseInfo houseInfo;
	
	/** 举报的房源信息id**/
	private long houseInfo_id;
	
//	/** 举报人员 **/
//	private Member member;

	/** 举报人员id**/
	private long member_id;
	
	/** 举报信息状态 **/
	private REPORT_STATUS status;
	
	/** 举报返回的审核原因是**/
	private String checkContent;

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}


	public REPORT_STATUS getStatus() {
		return status;
	}

	public void setStatus(REPORT_STATUS status) {
		this.status = status;
	}

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

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

}
