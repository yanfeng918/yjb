package com.yongjinbao.finance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.finance.dao.IIncomeDao;
import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.dto.GetIncomeExpenseDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.finance.vo.IncomeExpenseVO;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
@Repository
public class IncomeDao extends BaseDaoImpl<Income,Integer> implements IIncomeDao {

	@Override
	public List<Income> getIncomeList(GetIncomeDto getIncomeDto) {
        List<Income> list=getSqlSession().selectList(Income.class.getName()+".getIncomeList",getIncomeDto);
        return list;
	}

    @Override
    public int getIncomeListCount(GetIncomeDto getIncomeDto) {
        int count=getSqlSession().selectOne(Income.class.getName()+".getIncomeListCount",getIncomeDto);
        return count;
    }

	@Override
	public void addIncomeInfo(Income income) {
		getSqlSession().insert(Income.class.getName()+".add", income);
	}
	
	@Override
	public List<IncomeExpenseVO> getMyIncomeExpenseList(GetIncomeExpenseDto getIncomeExpenseDto) {
		return getSqlSession().selectList(Income.class.getName()+".getIncomeExpenseList", getIncomeExpenseDto);
	}
	
	@Override
	public int getIncomeExpenseCount(GetIncomeExpenseDto getIncomeExpenseDto) {
		return getSqlSession().selectOne(Income.class.getName()+".getIncomeExpenseCount", getIncomeExpenseDto);
	}
	
	public float getIncomAmountYesterDay(long member_id) {
		Float sum = getSqlSession().selectOne(Income.class.getName()+".getIncomAmountYesterDay",
				+member_id);
		return sum==null?0f:sum;
	}
	
	public float getAllIncomAmount(long member_id) {
		Float sum =  getSqlSession().selectOne(Income.class.getName()+".getAllIncomAmount",
				+member_id);
		return sum==null?0f:sum;
	}
	
	@Override
	public IncomeAmountVO getIncomeAmountByDate(long member_id) {
		IncomeAmountVO amountVO = new IncomeAmountVO();
		amountVO.setYesterdaySum(getIncomAmountYesterDay(member_id));
		return amountVO;
	} 
	
}
