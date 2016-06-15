package com.yongjinbao.houseValid.vo;

/**
 * Created by fddxiaohui on 2015/11/4.
 */
public class BatchCheckMobileVO {
    private String mobile;
    private String reason;
    private boolean isOperate;

    public boolean isOperate() {
        return isOperate;
    }

    public void setIsOperate(boolean isOperate) {
        this.isOperate = isOperate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
