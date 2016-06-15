package com.yongjinbao.mybatis.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yongjinbao.beans.Principal;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.dao.IBaseDao;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;
import com.yongjinbao.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;


public class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {

	 private IBaseDao<T, PK> baseDao;

	 public IBaseDao<T, PK> getBaseDao(){
		 return this.baseDao;
	 }

	 public void setBaseDao(IBaseDao<T, PK> baseDao){
		 this.baseDao = baseDao;
	 }

    public long getMemberId(HttpServletRequest request){
        String token = WebUtils.getCookie(request, Member.USERNAME_COOKIE_NAME);
        byte[] bs = RedisUtils.get(SerializeUtil.serialize(token));
        Principal principal = (Principal) SerializeUtil.unserialize(bs);
        return principal.getId();
    }

	@Override
	public T load(PK id) {
		return baseDao.load(id);
	}

	@Override
	public void add(T entity) {
		baseDao.add(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(PK id) {
		baseDao.delete(id);
	}

	@Override
	public List<T> list(Map<String,Object> params) {
		return baseDao.list(params);
	}
	
	@Override
	public List<T> list(String sqlId,Map<String,Object> params) {
		return baseDao.list(sqlId, params);
	}

	@Override
	public Pager<T> find(Map<String,Object> params) {
		return baseDao.find(params);
	}

	@Override
	public Pager<T> find(String sqlId,Map<String,Object> params) {
		return baseDao.find(sqlId, params);
	}

}
