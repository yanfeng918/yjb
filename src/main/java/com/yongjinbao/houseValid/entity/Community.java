package com.yongjinbao.houseValid.entity;

import com.yongjinbao.commons.entity.Area;

import java.io.Serializable;

/**
 *	Entity--小区/楼盘字典使用
 *	@author mashenwei
 *	@since 2015年11月11日
 */
public class Community implements Serializable {
	private static final long serialVersionUID = -8943133174766542281L;

	private Long id;
	
	private String name;
	
	private String address;
	
	private Long area_id;
	
	private Area area;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getArea_id() {
		return area_id;
	}

	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
