package com.yongjinbao.finance.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.finance.dao.IBankAccountDao;
import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.finance.service.IBankAccountService;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class BankAccountService extends BaseServiceImpl<BankAccount,Integer> implements IBankAccountService{
	
    @Inject
    private IBankAccountDao bankAccountDao;
    
    @Inject
    public void setBaseDao(IBankAccountDao bankAccountDao) {
        super.setBaseDao(bankAccountDao);
    }

    @Override
    public List<BankAccount> getBankList(long member_id) {
         List<BankAccount> list=bankAccountDao.getBankList(member_id);
         return list;
    }
}
