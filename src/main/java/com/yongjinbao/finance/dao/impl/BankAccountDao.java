package com.yongjinbao.finance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.finance.dao.IBankAccountDao;
import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
@Repository
public class BankAccountDao extends BaseDaoImpl<BankAccount,Integer> implements IBankAccountDao {

    @Override
    public List<BankAccount> getBankList(long member_id) {
        List<BankAccount> list= getSqlSession().selectList(BankAccount.class.getName()+".getBankList",member_id);
        return list;
    }
	
}
