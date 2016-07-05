/*
 * 
 * 
 * 
 */
package com.yongjinbao.commons.entity;

import com.yongjinbao.utils.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.Serializable;
import java.util.Date;

import javax.validation.groups.Default;

/**
 * Entity - 基类
 * 
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -67188388306700736L;

	/** "ID"属性名称 */
	public static final String ID_PROPERTY_NAME = "id";

	/** "创建日期"属性名称 */
	public static final String CREATEDATE_PROPERTY_NAME = "createDate";

	/** "修改日期"属性名称 */
	public static final String MODIFYDATE_PROPERTY_NAME = "modifyDate";

	/**
	 * 保存验证组
	 */
	public interface Save extends Default {

	}

	/**
	 * 更新验证组
	 */
	public interface Update extends Default {

	}

	/** ID */
	private Long id;

	/** 创建日期 */
	private Date createDate;

	/** 修改日期 */
	private Date modifyDate;

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	// MySQL/SQLServer: @GeneratedValue(strategy = GenerationType.AUTO)
	// Oracle: @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	public Long getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取创建日期
	 * 
	 * @return 创建日期
	 */
	public Date getCreateDate() {

		System.out.print(1123);
		return DateUtils.dateToFormatDate(createDate);
	}

	/**
	 * 设置创建日期
	 * 
	 * @param createDate
	 *            创建日期
	 */
	public void setCreateDate(Date createDate) {
		System.out.print(1123);
		this.createDate = DateUtils.dateToFormatDate(createDate);
	}

	/**
	 * 获取修改日期
	 * 
	 * @return 修改日期
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * 设置修改日期
	 * 
	 * @param modifyDate
	 *            修改日期
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 重写equals方法
	 * 
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	/**
	 * 重写hashCode方法
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}

}