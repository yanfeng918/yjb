package com.yongjinbao.finance.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;

/**
 * Entity - 用户充值明细
 * 
 */

public class WithDraw extends BaseEntity{
	
	private static final long serialVersionUID = -5710237822110161986L;

	/** 提现数量 */
	private float amount;
	
	/** 提现状态(成功、失败) */
	public enum WithDraw_STATUS{
		APPLY("待审核",1),CHECKING("审核中",2),SUCCESS("成功",3),FAIL("失败",4);
        private String outername;
        private int value;
        private WithDraw_STATUS(String outername,int value){
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

	/** 审核内容 */
	private String checkContent;
	
    /**提现状态**/
	private WithDraw_STATUS status;
	/** 所属会员 */
	private Member member;
	
	/** 所属会员账户 */
	private BankAccount bankAccount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

    public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
    public WithDraw_STATUS getStatus() {
        return status;
    }
    public void setStatus(WithDraw_STATUS status) {
        this.status = status;
    }

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
}
