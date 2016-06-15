package com.yongjinbao.houseNew.dto;

/**
 * Created by fddxiaohui on 2015/9/6.
 */
public class FixedHouseInfoGto {
    /** 房源业主姓名 */
    private String nameFixed;
    /** 房源业主手机 */
    private String mobileFixed;

    /** 小区 */
    private String communityFixed;
    /** 房型 （几室几厅） */
    private String houseShapeFixed;

    /** 面积 */
    private int areaSizeFixed;
    /** 楼栋 */
    private String banFixed;
    /** 楼层 */
    private String floorFixed;
    /** 房间号 */
    private String roomNumberFixed;
    /** 价格 */
    private int salePriceFixed;
    
    private String descriptionFixed;
    
    private String addressFixed;

	public String getDescriptionFixed() {
		return descriptionFixed;
	}

	public void setDescriptionFixed(String descriptionFixed) {
		this.descriptionFixed = descriptionFixed;
	}

	public String getAddressFixed() {
		return addressFixed;
	}

	public void setAddressFixed(String addressFixed) {
		this.addressFixed = addressFixed;
	}

	public String getNameFixed() {
        return nameFixed;
    }

    public void setNameFixed(String nameFixed) {
        this.nameFixed = nameFixed;
    }

    public String getMobileFixed() {
        return mobileFixed;
    }

    public void setMobileFixed(String mobileFixed) {
        this.mobileFixed = mobileFixed;
    }

    public String getCommunityFixed() {
        return communityFixed;
    }

    public void setCommunityFixed(String communityFixed) {
        this.communityFixed = communityFixed;
    }

    public String getHouseShapeFixed() {
        return houseShapeFixed;
    }

    public void setHouseShapeFixed(String houseShapeFixed) {
        this.houseShapeFixed = houseShapeFixed;
    }

    public int getAreaSizeFixed() {
        return areaSizeFixed;
    }

    public void setAreaSizeFixed(int areaSizeFixed) {
        this.areaSizeFixed = areaSizeFixed;
    }

    public String getBanFixed() {
        return banFixed;
    }

    public void setBanFixed(String banFixed) {
        this.banFixed = banFixed;
    }

    public String getFloorFixed() {
        return floorFixed;
    }

    public void setFloorFixed(String floorFixed) {
        this.floorFixed = floorFixed;
    }

    public String getRoomNumberFixed() {
        return roomNumberFixed;
    }

    public void setRoomNumberFixed(String roomNumberFixed) {
        this.roomNumberFixed = roomNumberFixed;
    }

    public int getSalePriceFixed() {
        return salePriceFixed;
    }

    public void setSalePriceFixed(int salePriceFixed) {
        this.salePriceFixed = salePriceFixed;
    }
}
