package com.yongjinbao.customerinfo.entity;



import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;
/**
 * Entity - 客源信息
 * 
 */

public class CustomerInfo extends BaseEntity{
	private static final long serialVersionUID = -286763896768717239L;
    /*
     * 信息的状态
     */
    public enum CUSTOMERINFO_STATUS{
        APPLY("申请"),CHECK("审核"),SUCCESS("成功"),FAIL("失败");
        private String outername;
        private CUSTOMERINFO_STATUS(String outername){
            this.outername = outername;
        }
        public String getOutername() {
            return outername;
        }
    }
    /*
     * 信息的出售方式
     */
    public enum CUSTOMERINFO_SALEWAY{
        SYSTEM("出售系统"),CUSTOMER("自由出售");
        private String outername;
        private CUSTOMERINFO_SALEWAY(String outername){
            this.outername = outername;
        }
        public String getOutername() {
            return outername;
        }
    }

    /** 房源业主姓名 */
    private String name;
    /** 房源业主电话 */
    private String phone;
    /** 房源业主手机 */
    private String mobile;
    /** 城市 */
    private int city_id;
    /** 区域 */
    private int region_id;
    /** 板块 */
    private int town_id;
    /** 小区 */
    private String community;
    /** 地址 */
    private String address;
    /** 面积 */
    private int areaSize;
    /** 楼栋 */
    private String ban;
    /** 楼层 */
    private String floor;
    /** 房间号 */
    private String roomNumber;
    /** 树路径分隔符 */
    private String houseDespt;
    /** 价格 */
    private int salePrice;

    //特有的属性：
    /** 信息的价格 */
    private int infoPrice;
    /** 信息的出售方式  出售系统 自己出售*/
    private CUSTOMERINFO_SALEWAY saleWay;
    /** 信息的状态  审核中 通过 无效*/
    private CUSTOMERINFO_STATUS status;

    /** 信息的查看次数*/
    private int readTime;

    /** 信息是否唯一*/
    private boolean isSingle;

    /** 信息是否可用 */
    private boolean isAvailable;
    /** 关联的区域对象 */
    private Area area;

    /** 关联的会员对象 */
    private Member member;

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

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public int getTown_id() {
        return town_id;
    }

    public void setTown_id(int town_id) {
        this.town_id = town_id;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(int areaSize) {
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

    public String getHouseDespt() {
        return houseDespt;
    }

    public void setHouseDespt(String houseDespt) {
        this.houseDespt = houseDespt;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getInfoPrice() {
        return infoPrice;
    }

    public void setInfoPrice(int infoPrice) {
        this.infoPrice = infoPrice;
    }

    public CUSTOMERINFO_SALEWAY getSaleWay() {
        return saleWay;
    }

    public void setSaleWay(CUSTOMERINFO_SALEWAY saleWay) {
        this.saleWay = saleWay;
    }

    public CUSTOMERINFO_STATUS getStatus() {
        return status;
    }

    public void setStatus(CUSTOMERINFO_STATUS status) {
        this.status = status;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
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
}
