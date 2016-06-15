package com.yongjinbao.finance.service;

import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.mybatis.service.IBaseService;

import java.util.List;

/**
 * Created by yanfeng on 2015/8/17.
 * alter by xiaohui on 2015/8/18
 */
public interface IBankAccountService extends IBaseService<BankAccount,Integer>{
    /**
     * 获取个人银行卡账户列表
     */
    public List<BankAccount> getBankList(long member_id);
	
}
