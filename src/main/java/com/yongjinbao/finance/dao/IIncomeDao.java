package com.yongjinbao.finance.dao;

import java.util.List;

import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.dto.GetIncomeExpenseDto;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.finance.vo.IncomeExpenseVO;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
public interface IIncomeDao extends IBaseDao<Income,Integer> {
	/**
	 * 收入记录查询
	 * @return
	 */
	public List<Income> getIncomeList(GetIncomeDto getIncomeDto);
    /**
     * 获取总数
     */
    public int getIncomeListCount(GetIncomeDto getIncomeDto);
    /**
     * 新增收入明细
     */
    public void addIncomeInfo(Income income);
    
    /** 【收支明细】 获取我的收支明细记录LIST**/
    public List<IncomeExpenseVO> getMyIncomeExpenseList(GetIncomeExpenseDto getIncomeExpenseDto);
    
    /** 【收支明细】 获取我的收支明细数**/
    public int getIncomeExpenseCount(GetIncomeExpenseDto getIncomeExpenseDto);
    
    /**【获取昨日/全部收益】**/
    public IncomeAmountVO getIncomeAmountByDate(long member_id);
}
