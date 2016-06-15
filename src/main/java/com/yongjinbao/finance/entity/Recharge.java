package com.yongjinbao.finance.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;

/**
 * Entity - 用户充值明细
 * 
 */

public class Recharge extends BaseEntity{
	private static final long serialVersionUID = -2811701185285547407L;
	
	/** 充值数量 */
	private String amount;
	
	/** 充值状态(待审核、审核中、成功、失败) */
	private Recharge_STATUS status;
    private String statsName;
    
    private String checkContent;
	
	/**
	 * 信息的状态 
	 */
	public enum Recharge_STATUS{
		APPLY("待审核",1),CHECKING("审核中",2),SUCCESS("成功",3),FAIL("失败",4);
        private String outername;
        private int value;
        private Recharge_STATUS(String outername,int value){
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

    public String getStatsName() {
        return statsName;
    }

    /** 所属会员 */
	private Member member;
	
	/** 所属会员账户 */
	private BankAccount bankAccount;
	
	/** 充值单号 */
	private String rechargeNumber;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Recharge_STATUS getStatus() {
		return status;
	}

	public void setStatus(Recharge_STATUS status) {
		this.status = status;
        this.statsName=status.getOutername();
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

	public String getRechargeNumber() {
		return rechargeNumber;
	}

	public void setRechargeNumber(String rechargeNumber) {
		this.rechargeNumber = rechargeNumber;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	
}
