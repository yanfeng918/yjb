package com.yongjinbao.commons.vo;

import java.util.Date;

/**
 * Created by fddxiaohui on 2015/8/31.
 */
public class BaseVO {
    /**开始时间**/
    private Date createDate;
    /**结束时间**/
    private Date modifyDate;
    /**会员ID**/
    private long Member_id;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getMember_id() {
        return Member_id;
    }

    public void setMember_id(long member_id) {
        Member_id = member_id;
    }
}
