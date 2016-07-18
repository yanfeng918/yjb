package com.yongjinbao.finance.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.enums.expense.EXPENSES_TYPE;
import com.yongjinbao.enums.house.HouseTypeEnum;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.entity.Member;
/**
 * Entity - 用户消费明细
 * 
 */
public class Expenses extends BaseEntity{
	private static final long serialVersionUID = 6020976395339131285L;


    /** 收入数量 */
	private String amount;
	
	/** 所属会员 */
	private Member member;
    /**消费类型**/
    private int expensesType;
    
    /**消费对象**/
    private long expensesTo;

    /** 收入房源id **/
    private long houseInfo_id;
    
    private HouseInfo houseInfo;
    
    private String statusName;

	private int houseType;

	public int getExpensesType() {
		return expensesType;
	}

	public void setExpensesType(int expensesType) {
		this.expensesType = expensesType;
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

	public int getHouseType() {
		return houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}
}
