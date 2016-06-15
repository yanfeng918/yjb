package com.yongjinbao.houseinfo.dto;

import com.yongjinbao.commons.dto.BaseDto;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
public class GetHouseInfoDto extends BaseDto {
	
	
	/**房型**/
	private String houseShape;
    /**售价区间**/
    private String salePrice;
    /**房屋描述**/
    private String description;
    /**房屋面积区间**/
    private String areaSize;
    /**区域id**/
    private int area_id;

    /**小区名称**/
    private String community;
    /**地址**/
    private String address;
    /**最大居住面积**/
    private int maxAreaSize;
    /**最小居住面积**/
    private int minAreaSize;
    /**最高售价**/
    private int maxSalePrice;
    /**最低售价**/
    private int minSalePrice;
    /**信息价格最大值**/
    private int maxInfoPrice;
    /**信息价格最小值**/
    private int minInfoPrice;
    /**对状态**/
    private String status;
    /**售卖方式**/
    private String saleWay;
    /**信息是否可用**/
    private boolean available;

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(String areaSize) {
        this.areaSize = areaSize;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
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

    public int getMaxAreaSize() {
        return maxAreaSize;
    }

    public void setMaxAreaSize(int maxAreaSize) {
        this.maxAreaSize = maxAreaSize;
    }

    public int getMinAreaSize() {
        return minAreaSize;
    }

    public void setMinAreaSize(int minAreaSize) {
        this.minAreaSize = minAreaSize;
    }

    public int getMaxSalePrice() {
        return maxSalePrice;
    }

    public void setMaxSalePrice(int maxSalePrice) {
        this.maxSalePrice = maxSalePrice;
    }

    public int getMinSalePrice() {
        return minSalePrice;
    }

    public void setMinSalePrice(int minSalePrice) {
        this.minSalePrice = minSalePrice;
    }

    public int getMaxInfoPrice() {
        return maxInfoPrice;
    }

    public void setMaxInfoPrice(int maxInfoPrice) {
        this.maxInfoPrice = maxInfoPrice;
    }

    public int getMinInfoPrice() {
        return minInfoPrice;
    }

    public void setMinInfoPrice(int minInfoPrice) {
        this.minInfoPrice = minInfoPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSaleWay() {
        return saleWay;
    }

    public void setSaleWay(String saleWay) {
        this.saleWay = saleWay;
    }

	public String getHouseShape() {
		return houseShape;
	}

	public void setHouseShape(String houseShape) {
		this.houseShape = houseShape;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
