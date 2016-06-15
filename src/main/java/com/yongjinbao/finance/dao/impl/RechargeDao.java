package com.yongjinbao.finance.dao.impl;

import java.util.List;

import com.yongjinbao.finance.dto.ApplyRechargeDto;
import com.yongjinbao.finance.dto.GetRechargeDto;
import org.springframework.stereotype.Repository;

import com.yongjinbao.finance.dao.IRechargeDao;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
@Repository
public class RechargeDao extends BaseDaoImpl<Recharge,Integer> implements IRechargeDao {

	@Override
	public List<Recharge> getRechargeList(GetRechargeDto getRechargeDto) {
		// TODO Auto-generated method stub
        List<Recharge> list=getSqlSession().selectList(Recharge.class.getName()+".getRechargeList",getRechargeDto);
        return list;
	}

    @Override
    public int getRechargeListCount(GetRechargeDto getRechargeDto) {
        int count=getSqlSession().selectOne(Recharge.class.getName()+".getRechargeListCount",getRechargeDto);
        return count;
    }
    
    @Override
    public boolean addRecharge(Recharge recharge) {
    	// TODO Auto-generated method stub
    	int add = getSqlSession().insert(Recharge.class.getName()+".addRecharge", recharge);
    	return add>0;
    }
}
