package com.yongjinbao.finance.dao;

import java.util.List;

import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
public interface IExpensesDao extends IBaseDao<Expenses,Integer> {

	
	/**
	 * 消费记录查询
	 * @return
	 */
	public List<Expenses> getExpensesList(GetExpensesDto getExpensesDto);
    /**
     * 获取总数
     */
    public int getExpenseListCount(GetExpensesDto getExpensesDto);
    /**
     * 新增 【消费明细】信息
     */
    public void addExpenseInfo(Expenses expenses);
    
//    public List<Expenses> getSystemExpenseList(GetExpensesDto getExpensesDto);
//    
//    public int getSystemExpenseListCount(GetExpensesDto getExpensesDto);
}
