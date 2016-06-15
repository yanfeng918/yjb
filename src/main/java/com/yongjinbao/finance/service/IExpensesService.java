package com.yongjinbao.finance.service;

import java.util.List;

import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by yanfeng on 2015/8/17.
 * alter by xiaohui on 2015/8/18
 */
public interface IExpensesService extends IBaseService<Expenses,Integer>{
	
	/**
	 * 消费记录查询
	 */
	public <T>Pager<T> getExpensesList(GetExpensesDto getExpensesDTO);
	/**
     * 新增 【消费明细】信息
     */
    public void addExpenseInfo(Expenses expenses);
    
//    /**
//	 * 系统支出
//	 */
//	public <T>Pager<T> getSystemExpensesList();
}
