package com.yongjinbao.finance.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;

public class BankAccount extends BaseEntity{
	
	private static final long serialVersionUID = -5638694940296864203L;
	
	/** 账户号码 */
	private String accountNum;
	/** 支行名称 */
	private String branchName;
	/** 银行开户地址 */
	private String bankAddress;
	/** 开户人 */
	private String accountHolder;
	/** 是否可用 */
	private boolean available;
	
	/** 所属会员 */
	private Member member;
	
    public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public boolean getAvailable() {
		return available;

	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
}
