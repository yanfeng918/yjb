package com.yongjinbao.customerinfo.dto;

import com.yongjinbao.commons.dto.BaseDto;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
public class GetCustomerInfoDto extends BaseDto {
    /**对应城市id**/
    private int city_id;
    /**对应区域id**/
    private int region_id;
    /**对应板块id**/
    private int town_id;
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
}
