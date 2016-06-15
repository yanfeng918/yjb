package com.yongjinbao.mybatis.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.yongjinbao.mybatis.dao.IBaseDao;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;



@Repository
public class BaseDaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport implements IBaseDao<T, PK> {
	
	@Resource(name="sqlSessionTemplate")
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		 super.setSqlSessionTemplate(sqlSessionTemplate);
     }
	
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	@Override
	public void add(T obj) {
		getSqlSession().insert(obj.getClass().getName()+".add",obj);
	}
	@Override
	public void add(String sqlId,Object obj) {
		getSqlSession().insert(sqlId,obj);
	}
	@Override
	public void update(T obj) {
		getSqlSession().update(obj.getClass().getName()+".update", obj);
	}
	@Override
	public void delete(PK id) {
		getSqlSession().delete(getClz().getName()+".delete", id);
	}
	@Override
	public void delete(String sqlId,Map<String,Object> params) {
		getSqlSession().delete(sqlId, params);
	}
	@Override
	@SuppressWarnings("unchecked")
	public T load(PK id) {
		return (T)getSqlSession().selectOne(getClz().getName()+".load", id);
	}
	@Override
	@SuppressWarnings("unchecked")
	public T loadBySqlId(String sqlId,Map<String,Object> params) {
		return (T)getSqlSession().selectOne(sqlId, params);
	}
	@Override
	@SuppressWarnings("unchecked")
	public T loadBySqlId(String sqlId,Object obj) {
		return (T)getSqlSession().selectOne(sqlId,obj);
	}
	@Override
	public List<T> list(Map<String,Object> params) {
		return this.list(getClz().getName()+".list", params);
	}
	@Override
	public List<T> list(String sqlId,Map<String,Object> params) {
		List<T> list = null;
		list = getSqlSession().selectList(sqlId, params);
		return list;
	}
	@Override
	public Pager<T> find(Map<String,Object> params) {
		return this.find(getClz().getName()+".find", params);
	}
	@Override
	public Pager<T> find(String sqlId,Map<String,Object> params) {
		int pageSize = SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Pager<T> pages = new Pager<T>();
	
		if(params==null) params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("pageOffset", pageOffset);
		params.put("sort", sort);
		params.put("order", order);
		List<T> datas = getSqlSession().selectList(sqlId, params);
		pages.setList(datas);
		pages.setPageOffset(pageOffset);
		pages.setPageSize(pageSize);
		int totalCount = getSqlSession().selectOne(sqlId+"_count",params);
		pages.setTotalCount(totalCount);
		return pages;
	}
	
}
