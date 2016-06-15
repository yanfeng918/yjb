package com.yongjinbao.collection.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.entity.BaseEntity;

/**
 * 征集房源
 * @author mashenwei
 * @since 2015/09/14
 */
public class Collection extends BaseEntity {

	private static final long serialVersionUID = -7095110003612828924L;
	
	/** 【征集房源】 征集价格 **/
	private float collectPrice;
	
	/** 【征集房源】 区域 **/
	private long area_id;
	
	private Area area;
	
	/** 【征集房源】 小区 **/
	private String community="";
	
	/** 【征集房源】 楼栋 **/
	private String ban;
	
	/** 【征集房源】 楼层 **/
	private String floor;
	
	/** 【征集房源】 房间号 **/
	private String roomNumber;
	
	/** 【征集房源】 户型 **/
	private String houseShape;
	
	/** 【征集房源】 面积 **/
	private float areaSize;
	
	/** 【征集房源】 售价 **/
	private float salePrice;
	
	/** 【征集房源】 征集人 **/
	private long member_id;
	
	/** 【征集房源】 描述 **/
	private String description;
	
	/** 【征集房源】 地址 **/
	private String address;
	
	/** 【征集房源】 关闭征集原因 **/
	private String endReason;
	
	/** 【征集房源】关闭时间*/
	private Date endDate;
	
	/** 【征集房源】征集状态*/
	private Collection_STATUS status;
	
	private String statusName;
	/*
	 * 征集状态 
	 */
	public enum Collection_STATUS{

		COLLECTING("正在征集",1),CUSTOMERCLOSE("您已关闭",2),SYSTEMCLOSE("系统关闭",3);

        private String outername;
        private int value;
        private Collection_STATUS(String outername,int value){
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

	public String getAddress() {
		if (StringUtils.isEmpty(address)||address.equals("null")) {
			address = "";
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = StringUtils.isEmpty(address)?"":address;
	}

	public float getCollectPrice() {
		return collectPrice;
	}

	public void setCollectPrice(float collectPrice) {
		this.collectPrice = collectPrice;
	}

	public long getArea_id() {
		return area_id;
	}

	public void setArea_id(long area_id) {
		this.area_id = area_id;
	}

	public String getCommunity() {
		if (StringUtils.isEmpty(community)||community.equals("null")) {
			community = "";
		}
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getBan() {
		if (StringUtils.isEmpty(ban)||ban.equals("null")) {
			ban = "";
		}
		
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getFloor() {
		if (StringUtils.isEmpty(floor)||floor.equals("null")) {
			floor = "";
		}
		return floor;
	}

	public void setFloor(String floor) {
			this.floor = floor;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		if (roomNumber == null ||roomNumber.equals("")) {
			this.roomNumber = "不限";
		}else {
			this.roomNumber = roomNumber;
		}
	}

	public String getHouseShape() {
		return houseShape;
	}

	public void setHouseShape(String houseShape) {
		if (houseShape.equals("零室零厅")) {
			this.houseShape = "不限";
		}else {
			this.houseShape = houseShape;
		}
	}

	public float getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(float areaSize) {
		this.areaSize = areaSize;
	}

	public float getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null|| description.equals("")) {
			this.description = "不限";
		}else {
			this.description = description;
		}
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Collection_STATUS getStatus() {
		return status;
	}

	public void setStatus(Collection_STATUS status) {
		this.status = status;
		this.statusName=status.getOutername();
	}
	
	public String getStatusName() {
        return statusName;
    }
	
}
