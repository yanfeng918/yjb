package com.yongjinbao.mybatis.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yongjinbao.mybatis.entity.Pager;

import javax.servlet.http.HttpServletRequest;


/**
 * Service接口 - Service接口基类
 */

public interface IBaseService<T, PK extends Serializable> {

    /**
     * 获取登入用户id
     * @param request
     * @return
     */
    public long getMemberId(HttpServletRequest request);
	/**
	 * 根据ID获取实体对象.
	 * @param id 记录ID
	 * @return 实体对象
	 */
	public T load(PK id);

	/**
	 * 获取所有实体对象总数.
	 * @return 实体对象总数
	 * public Long getTotalCount();
	 */
	

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 * public boolean isUnique(String propertyName, Object oldValue, Object newValue);
	 */
	
	/**
	 * 保存实体对象.
	 * @param entity 对象
	 * @return ID
	 */
	public void add(T entity);
	
	/**
	 * 更新实体对象.
	 * @param entity 对象
	 */
	public void update(T entity);

	/**
	 * 根据ID删除实体对象.
	 * @param id 记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * @param ids ID数组
	 * public void delete(PK[] ids);
	 */
	public List<T> list(Map<String,Object> params);
	public List<T> list(String sqlId,Map<String,Object> params);

	public Pager<T> find(Map<String,Object> params);
	public Pager<T> find(String sqlId,Map<String,Object> params);


}
