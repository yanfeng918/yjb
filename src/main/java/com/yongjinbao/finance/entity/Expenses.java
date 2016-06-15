package com.yongjinbao.finance.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.entity.Member;
/**
 * Entity - 用户消费明细
 * 
 */
public class Expenses extends BaseEntity{
	private static final long serialVersionUID = 6020976395339131285L;
    /** 支出状态 */
    public enum EXPENSES_TYPE{
        refund("系统扣款",1),dealExpense("交易支出",2),promoteWelfare("推广福利",3),
		extraAward("额外奖励",4),activityReward("活动奖励",5);
        private String outername;
        private int value;
        private EXPENSES_TYPE(String outername,int value){
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

    /** 收入数量 */
	private String amount;
	
	/** 所属会员 */
	private Member member;
    /**消费类型**/
    private EXPENSES_TYPE expensesType;
    
    /**消费对象**/
    private long expensesTo;

    /** 收入房源id **/
    private long houseInfo_id;
    
    private HouseInfo houseInfo;
    
    private String statusName;

    public EXPENSES_TYPE getExpensesType() {
        return expensesType;
    }

    public void setExpensesType(EXPENSES_TYPE expensesType) {
        this.expensesType = expensesType;
        this.setStatusName(expensesType.getOutername());
    }

    public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public long getExpensesTo() {
		return expensesTo;
	}

	public void setExpensesTo(long expensesTo) {
		this.expensesTo = expensesTo;
	}

	public long getHouseInfo_id() {
		return houseInfo_id;
	}

	public void setHouseInfo_id(long houseInfo_id) {
		this.houseInfo_id = houseInfo_id;
	}

	public String getStatusName() {
		return statusName;
	}
	
	public void setStatusName(String statusName) {
		if (statusName.equals("推广福利")) {
			statusName = "好友发布房源提成！";
		}else if (statusName.equals("交易支出")) {
			statusName = "用户发布房源收入";
		}else {
			
		}
		this.statusName = statusName;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	
}
