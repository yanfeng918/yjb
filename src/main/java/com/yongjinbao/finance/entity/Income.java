package com.yongjinbao.finance.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;

/**
 * Entity - 用户收入明细
 * 
 */
public class Income extends BaseEntity{

    /** 收入状态 */
    public enum INCOME_TYPE{
        refund("系统退款",1),dealIncome("交易收入",2),inviteReleaseIncome("好友发布有效房源",3),
        extraAward("额外奖励",4),activityAward(" 活动奖励",5);
        private String outername;
        private int value;
        private INCOME_TYPE(String outername,int value){
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
    
    public static void main(String[] args) {
    	System.out.println(INCOME_TYPE.refund.outername);
	}

	
	private static final long serialVersionUID = -505468391821260047L;

	/** 收入数量 */
	private float amount;
	
	/** 所属会员 */
	private Member member;
	
    /**收入类型**/
    private INCOME_TYPE incomeType;
    
    /**收入来源人ID **/
    private long incomeFrom;
    
    /** 收入房源id **/
    private long houseInfo_id;

    public INCOME_TYPE getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(INCOME_TYPE incomeType) {
        this.incomeType = incomeType;
    }

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

	public long getIncomeFrom() {
		return incomeFrom;
	}

	public void setIncomeFrom(long incomeFrom) {
		this.incomeFrom = incomeFrom;
	}

	public long getHouseInfo_id() {
		return houseInfo_id;
	}

	public void setHouseInfo_id(long houseInfo_id) {
		this.houseInfo_id = houseInfo_id;
	}
}
