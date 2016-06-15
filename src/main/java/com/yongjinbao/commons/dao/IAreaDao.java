/*
 * 
 * 
 * 
 */
package com.yongjinbao.commons.dao;

import java.util.List;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Dao - 地区
 * 
 */
public interface IAreaDao extends IBaseDao<Area, Long> {

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
	
	/** 根据全称获取区域ID **/
	public Long getAreaIdByName(String fullName);
	
	/** 根据名称获取区域ID **/
	public Area findByAreaName(String name);

}