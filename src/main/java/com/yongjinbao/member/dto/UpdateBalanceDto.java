package com.yongjinbao.member.dto;

import java.util.Date;

import com.yongjinbao.member.entity.Member;

/**
 * 更新余额
 */
public class UpdateBalanceDto {
	
	public static final int INCREASE = 1, REDUCE = 2;
	
	/** 【更新余额】的会员对象 */
	private Member member;
	
	/** 【更新余额】的数量 */
	private float updateAmount;
	
	/** 【更新余额】的会员id */
	private float laterBalance;
	
	/** 【增加余额】的会员id 
	 *	UpdateBalanceDto.INCREASE代表增加
	 *	UpdateBalanceDto.REDUCE代表减少
	 */
	public float getIncreaseAmount() {
		return updateAmount;
	}

	/**
	 * @param updateAmount 
	 * @param type  增加或减少
	 * 				UpdateBalanceDto.INCREASE代表增加
	 *				UpdateBalanceDto.REDUCE代表减少
	 */
	public void setUpdateAmount(float updateAmount, int type) {
		this.updateAmount = updateAmount;
		switch (type) {
		case INCREASE:
			this.laterBalance = member.getBalance() + updateAmount;
			break;
		case REDUCE:
			this.laterBalance = member.getBalance() - updateAmount;
			break;
		}
		
	}

	public float getLaterBalance() {
		return laterBalance;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
