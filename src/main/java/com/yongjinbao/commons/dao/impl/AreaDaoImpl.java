package com.yongjinbao.commons.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.commons.dao.IAreaDao;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Dao - 地区
 */
@Repository("areaDaoImpl")
public class AreaDaoImpl extends BaseDaoImpl<Area, Long> implements IAreaDao {

	public List<Area> findRoots() {
		List<Area> list=getSqlSession().selectList(Area.class.getName()+".findRoots",new Object());
		return list;
	}

	@Override
	public List<Area> findChildren(long id) {
		List<Area> list=getSqlSession().selectList(Area.class.getName()+".findChildren",id);
		return list;
	}
	
	@Override
	public Long getAreaIdByName(String fullName) {
		List<Long> list = getSqlSession().selectList(Area.class.getName()+".getAreaIdByName",fullName);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public Area findByAreaName(String name) {
		List<Area> list = getSqlSession().selectList(Area.class.getName()+".findByAreaName",name);
		if (list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
		
//		return getSqlSession().selectOne(Area.class.getName()+".findByAreaName",name);
	}
	

}