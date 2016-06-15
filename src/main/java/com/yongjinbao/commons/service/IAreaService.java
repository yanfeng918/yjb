/*
 * 
 * 
 * 
 */
package com.yongjinbao.commons.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Service - 地区
 * 
 */
public interface IAreaService extends IBaseService<Area, Long> {

	/**
	 * 查找顶级地区
	 * 
	 * @return 顶级地区
	 */
	List<Area> findRoots();
	
	/**
	 * 查找子级地区
	 * 
	 * @return 子级地区
	 */
	List<Area> findChildren(long id);
	
	/** 
	 * 根据全称获取区域ID （有待于优化）
	 * [从Excel批量导入数据的时候使用]
	 */
	public Long getAreaIdByName(String fullName);
	
	/**
	 * 根据区域的名称添加区域
	 * 
	 * @return 子级地区
	 */
	public void addAreaByName(String areaName);
	
	/**
	 * 根据区域的id获取区域所在的城市
	 */
	public Long getCityByArea(Long areaId);
	
	/**
	 * 根据areaId判断是否同一区域
	 * @return
	 */
	public boolean isAreaFromSame(HttpServletRequest request, Long areaId2);
	
}