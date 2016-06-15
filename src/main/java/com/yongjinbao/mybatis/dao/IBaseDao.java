package com.yongjinbao.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yongjinbao.mybatis.entity.Pager;



/**
 * 公共的DAO处理对象，这个对象中包含了mybatis的所有基本操作和对SQL的操
 * @author Administrator
 *
 * @param <T>
 */
public interface IBaseDao<T, PK extends Serializable> {
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public void add(T t);
	
	public void add(String sqlId,Object obj);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(PK id);
	
	public void delete(String sqlId,Map<String,Object> params);

	public T load(PK id);
	public T loadBySqlId(String sqlId,Map<String,Object> params);
	public T loadBySqlId(String sqlId,Object obj);
	
	public List<T> list(Map<String,Object> params);
	public List<T> list(String sqlId,Map<String,Object> params);
	
	public Pager<T> find(Map<String,Object> params);
	public Pager<T> find(String sqlId,Map<String,Object> params);

	
	
	
	
	
}

