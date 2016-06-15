package com.yongjinbao.member.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.entity.Member;

public class BrowseFavoriteInfo extends BaseEntity{
	
	private static final long serialVersionUID = -5736496576360334753L;
	
    public enum CustomerHouseStyle{
        HouseInfo("房源信息"),CustomerInfo("客源信息");
        private String outername;
        private CustomerHouseStyle(String outername){
            this.outername = outername;
        }
        public String getOutername() {
            return outername;
        }
    }
    
    public enum BrowseFavoriteStyle{
    	Browse("浏览"),Favorites("收藏");
        private String outername;
        private BrowseFavoriteStyle(String outername){
            this.outername = outername;
        }
        public String getOutername() {
            return outername;
        }
    }
	
	/** 所属会员 */
	private Member member;
	/** 所属房源 */
	private HouseInfo houseInfo;

	/**
	 * 客源、房源信息类型
	 */
	public CustomerHouseStyle customerHouseStyle;
	
	/**
	 * 浏览 、收藏 信息类型
	 */
	public BrowseFavoriteStyle browseFavoriteStyle;
	
	
	public CustomerHouseStyle getCustomerHouseStyle() {
		return customerHouseStyle;
	}
	public void setCustomerHouseStyle(CustomerHouseStyle customerHouseStyle) {
		this.customerHouseStyle = customerHouseStyle;
	}
	public BrowseFavoriteStyle getBrowseFavoriteStyle() {
		return browseFavoriteStyle;
	}
	public void setBrowseFavoriteStyle(BrowseFavoriteStyle browseFavoriteStyle) {
		this.browseFavoriteStyle = browseFavoriteStyle;
	} 
	

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}
	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}
	
}
