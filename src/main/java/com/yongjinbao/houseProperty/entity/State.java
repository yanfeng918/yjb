package com.yongjinbao.houseProperty.entity;

import com.yongjinbao.commons.entity.BaseEntity;

/**
 * 房源申述表
 * Created by fddxiaohui on 2015/10/26.
 */
public class State extends BaseEntity {
    private static final long serialVersionUID = -375439799181740452L;

    /*
	 * 申述原因状态
	 */
    public enum stateStatus {
        APPLY("待审核", 1), CHECKING("审核中", 2), SUCCESS("成功", 3), FAIL("失败", 4);
        private String outername;
        private int value;

        private stateStatus(String outername, int value) {
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

    /**申述房源**/
    private long houseInfo_id;
    /**申述原因**/
    private String stateReason;
    /**申述状态**/
    private stateStatus stateStatus;
    /**操作人ID**/
    private  long operate_id;
    
    /**申诉人ID**/
    private  long member_id;
    /**
     * 申述结果
     *
     * @return
     */
    private String stateContent;

    public String getStateContent() {
        return stateContent;
    }

    public void setStateContent(String stateContent) {
        this.stateContent = stateContent;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public State.stateStatus getStateStatus() {
        return stateStatus;
    }

    public void setStateStatus(State.stateStatus stateStatus) {
        this.stateStatus = stateStatus;
    }

    public long getHouseInfo_id() {
        return houseInfo_id;
    }

    public void setHouseInfo_id(long houseInfo_id) {
        this.houseInfo_id = houseInfo_id;
    }

    public long getOperate_id() {
        return operate_id;
    }

    public void setOperate_id(long operate_id) {
        this.operate_id = operate_id;
    }

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
}
