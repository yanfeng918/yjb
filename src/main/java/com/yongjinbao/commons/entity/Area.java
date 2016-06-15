package com.yongjinbao.commons.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity - 地区
 * 
 */
public class Area extends BaseEntity {

	private static final long serialVersionUID = -2158109459123036967L;

	/** 树路径分隔符 */
	private static final String TREE_PATH_SEPARATOR = ",";

	/** 名称 */
	private String name;

	/** 全称 */
	private String fullName;

	/** 树路径 */
	private String treePath;

	/** 上级地区 */
	//private Area parent;
	
	private long parent;
	
	private String orders;

	/** 下级地区 */
	private Set<Area> children = new HashSet<Area>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取全称
	 * 
	 * @return 全称
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 设置全称
	 * 
	 * @param fullName
	 *            全称
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取上级地区
	 * 
	 * @return 上级地区
	 */
	/*public Area getParent() {
		return parent;
	}*/

	/**
	 * 设置上级地区
	 * 
	 * @param parent
	 *            上级地区
	 */
	/*public void setParent(Area parent) {
		this.parent = parent;
	}*/
	
	
	

	/**
	 * 获取下级地区
	 * 
	 * @return 下级地区
	 */
	public Set<Area> getChildren() {
		return children;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	/**
	 * 设置下级地区
	 * 
	 * @param children
	 *            下级地区
	 */
	public void setChildren(Set<Area> children) {
		this.children = children;
	}
	
	

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	/**
	 * 重写toString方法
	 * 
	 * @return 全称
	 */
	@Override
	public String toString() {
		return getFullName();
	}

}