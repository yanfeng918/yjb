package com.yongjinbao.finance.vo;


import com.yongjinbao.commons.vo.BaseVO;
import com.yongjinbao.finance.entity.WithDraw;

/**
 * Created by fddxiaohui on 2015/8/31.
 */
public class WithDrawVO extends BaseVO {
    /**提现状态**/
    private WithDraw.WithDraw_STATUS status;
    private String statusName;
    /**提现账户**/
    private String accountNum;
    /**提现金额**/
    private float amount;
    
    /** 审核内容 */
	private String checkContent;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public WithDraw.WithDraw_STATUS getStatus() {
        return status;
    }

    public void setStatus(WithDraw.WithDraw_STATUS status) {
        this.status = status;
        this.statusName=status.getOutername();
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getStatusName() {
        return statusName;
    }

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
}
