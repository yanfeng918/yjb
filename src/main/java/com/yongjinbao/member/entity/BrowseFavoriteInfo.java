package com.yongjinbao.member.entity;

import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.entity.Member;

public class BrowseFavoriteInfo extends BaseEntity{
	
	private static final long serialVersionUID = -5736496576360334753L;
	
    public enum HouseStyle{
        VALID("有效"),NEW("最新"),PROPERTY("物业");
        private String outername;
        private HouseStyle(String outername){
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
	private long houseInfoId;

	/**
	 * 客源、房源信息类型
	 */
	public HouseStyle houseStyle;
	
	/**
	 * 浏览 、收藏 信息类型
	 */
	public BrowseFavoriteStyle browseFavoriteStyle;


	public HouseStyle getHouseStyle() {
		return houseStyle;
	}

	public void setHouseStyle(HouseStyle houseStyle) {
		this.houseStyle = houseStyle;
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

	public long getHouseInfoId() {
		return houseInfoId;
	}

	public void setHouseInfoId(long houseInfoId) {
		this.houseInfoId = houseInfoId;
	}

}
