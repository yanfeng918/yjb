package com.yongjinbao.finance.dao;

import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.mybatis.dao.IBaseDao;

import java.util.List;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
public interface IBankAccountDao extends IBaseDao<BankAccount,Integer> {
    /**
     * 获取会员绑定的银行卡列表
     */
    public List<BankAccount> getBankList(long member_id);
	
}
