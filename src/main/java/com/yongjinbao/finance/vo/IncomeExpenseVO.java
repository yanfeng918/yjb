package com.yongjinbao.finance.vo;

import com.yongjinbao.commons.vo.BaseVO;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.houseinfo.entity.HouseInfo;

public class IncomeExpenseVO extends BaseVO {

	/** 【收支明细】 id**/
	private long id;
	
	/** 【收支明细】 明细金额**/
	private float amount;
	
	/** 【收支明细】 明细类型**/
	private String mtype;
	
	/** 【收支明细】 如果涉及到房源则返回房源信息id**/
	private Long houseInfo_id;
	
	/** 【收支明细】 返回房源**/
	private HouseInfo houseInfo;
	
	/** 【收支明细】 收支详细说明**/
	private String detail;
	
	/** 【收支明细】 收入或支出**/
	private boolean inOrNot;
	
	private String detailToString;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
		if (mtype.indexOf("收入")!=-1||mtype.indexOf("充值")!=-1) {
			setInOrNot(true);
		}else {
			setInOrNot(false);
		}
		setDetailToString(getDetailToString(mtype, detail));
	}

	public Long getHouseInfo_id() {
		return houseInfo_id;
	}

	public void setHouseInfo_id(Long houseInfo_id) {
		this.houseInfo_id = houseInfo_id;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
		setDetailToString(getDetailToString(mtype, detail));
	}

	public boolean isInOrNot() {
		return inOrNot;
	}

	private void setInOrNot(boolean inOrNot) {
		this.inOrNot = inOrNot;
	}
	
	private String getDetailToString(String mType, String detail){
		String detailToString="";
		if (mType!=null&&!mType.equals("")&&detail!=null&&!detail.equals("")) {
			if (mType.equals("收入")) {
				if (detail.equals("refund")) {
					detailToString = "系统退款";
				}else if (detail.equals("dealIncome")) {
					detailToString = "出售房源";
				}else if (detail.equals("inviteReleaseIncome")) {
					detailToString = "推广福利";
				}else if (detail.equals("extraAward")){
					detailToString = "系统月度奖励";
				}
			}else if (mType.equals("支出")) {
				if (detail.equals("refund")) {
					detailToString = "系统扣款";
				}else if (detail.equals("dealExpense")) {
					detailToString = "购买房源";
				}
			}else if (mType.equals("充值")||mType.equals("提现")) {
				detailToString = getCheckStatus(detail);
			}
		}
		return detailToString;
	}

	public String getDetailToString() {
		return detailToString;
	}

	public void setDetailToString(String detailToString) {
		this.detailToString = detailToString;
	}
	
	/**【充值或提现的当前审核状态】 **/
	private String getCheckStatus(String status){
		String statusToString="";
		switch (status) {
		case "APPLY":
			statusToString="待审核";
			break;
		case "CHECKING":
			statusToString="审核中";
			break;
		case "SUCCESS":
			statusToString="审核成功";
			break;
		case "FAIL":
			statusToString="审核失败";
			break;
		}
		return statusToString;
	}
}
