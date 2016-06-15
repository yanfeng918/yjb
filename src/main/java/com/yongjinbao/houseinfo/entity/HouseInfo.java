package com.yongjinbao.houseinfo.entity;

import java.util.Date;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;

/**
 * Entity - 房源信息
 * 
 */
public class HouseInfo extends BaseEntity{
	
	private static final long serialVersionUID = -375439799181740452L;
	
	//2015年12月2日15:20
	//3次未接通时显示未接通的次数
	private int disconTime;
	
	/** 【2015年10月12日 新增】优先级设定 
	 *  @规则描述 dagongpan用户自己录入的数据优先级最高
	 *  		批量导入的数据按每天导入量排。
	 *  		1-5分别代表优先级高低
	 * **/
	private int priority;
	
	/*
	 * 信息的状态 
	 */
	public enum HouseInfo_STATUS{

//		APPLY("待审核",1),CHECKING("审核",2),SUCCESS("成功",3),FAIL("失败",4);
		APPLY("待审核",1),CHECKING("审核中",2),SUCCESS("成功",3),FAIL("失败",4),
		FAIL_UNAVAILABLE("失败-房源失效",5),CHECKING_DISCON1("审核中-未接通1次",6),
		CHECKING_DISCON2("审核中-未接通2次",7),
		FAIL_DISCON3("失败-未接通3次",8),FAIL_SOLDOUT("失败-已出售",9),SUCCESS_DUPLICATE ("成功-重复",9);

        private String outername;
        private int value;
        private HouseInfo_STATUS(String outername,int value){
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
	/*
	 * 信息的出售方式 
	 */
	public enum HouseInfo_SaleWay{
        SYSTEM("出售系统",1),CUSTOMER("自由出售",2);
        private String outername;
        private int value;
        private HouseInfo_SaleWay(String outername,int value){
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
    /** 房源业主姓名 */
    private String name;
    /** 房源业主电话 */
    private String phone;
    /** 房源业主手机 */
    private String mobile;
    /** 小区 */
    private String community;
    /** 房型 （几室几厅） */
    private String houseShape;
    /** 面积 */
    private float areaSize;
    /** 楼栋 */
    private String ban;
    /** 楼层 */
    private String floor;
    /** 房间号 */
    private String roomNumber;
    /** 房源备注 */
    private String description;
    /** 价格 */
    private float salePrice;
    
    //特有的属性：
    /** 信息的价格 */
    private float infoPrice;
    /** 信息的出售方式  出售系统 自己出售*/
    private HouseInfo_SaleWay saleWay;
    /** 信息的状态  审核中 通过 无效*/
    private HouseInfo_STATUS status;
    private String statusName;

    /** 信息的查看次数*/
    private int readTime;

    /** 信息是否可用 */
    private boolean available;
    /** 关联的区域对象 */
    private Area area;

    /** 关联的会员对象 */
    private Member member;
    
    /**房源地址**/
    private String address;
    
    private Date checkDate;
    
    private String checkContent;
    /**
     * 导入或者用户自己输入房源标记：1：导入，null为自定义
     **/
    private Integer isChannel;

    public Integer getIsChannel() {
        return isChannel;
    }

    public void setIsChannel(Integer isChannel) {
        this.isChannel = isChannel;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }


	public String getHouseShape() {
		return houseShape;
	}

	public void setHouseShape(String houseShape) {
		this.houseShape = houseShape;
	}
    public float getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(float areaSize) {
        this.areaSize = areaSize;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public float getInfoPrice() {
        return infoPrice;
    }

    public void setInfoPrice(float infoPrice) {
        this.infoPrice = infoPrice;
    }


    public HouseInfo_SaleWay getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(HouseInfo_SaleWay saleWay) {
		this.saleWay = saleWay;
	}

	public HouseInfo_STATUS getStatus() {
		return status;
	}

	public void setStatus(HouseInfo_STATUS status) {
		this.status = status;
        this.statusName=status.getOutername();
	}

	public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    /**
     * 新增房源 -初始化若干属性
     */
    public void init(){
		setSaleWay(HouseInfo_SaleWay.CUSTOMER);
		setStatus(HouseInfo_STATUS.APPLY);
		setAvailable(true);
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getDisconTime() {
		return disconTime;
	}

	public void setDisconTime(int disconTime) {
		this.disconTime = disconTime;
	}

}
