package com.yongjinbao.finance.service;

import java.util.List;

import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.dto.GetIncomeExpenseDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.finance.vo.IncomeExpenseVO;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by yanfeng on 2015/8/17.
 * alter by xiaohui on 2015/8/18
 */
public interface IIncomeService extends IBaseService<Income,Integer>{
	/**
	 * 收入记录查询
	 */
	public <T>Pager<T> getIncomeList(GetIncomeDto getIncomeDto);
	
	/**
	 * 新增 【收入明细】信息
	 * @param income
	 */
	public void addIncomeInfo(Income income);
	
	/** 【收支明细】 获取我的收去明细记录**/
    public <T>Pager<T> getMyIncomeExpenseList(GetIncomeExpenseDto getIncomeExpenseDto);
    
    /**【获取昨日/全部收益】**/
    public IncomeAmountVO getIncomeAmountByDate(long member_id);
}
