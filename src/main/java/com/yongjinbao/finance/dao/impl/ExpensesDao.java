package com.yongjinbao.finance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.finance.dao.IExpensesDao;
import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
@Repository
public class ExpensesDao extends BaseDaoImpl<Expenses,Integer> implements IExpensesDao {

	@Override
	public List<Expenses> getExpensesList(GetExpensesDto getExpensesDto) {
		// TODO Auto-generated method stub
		List<Expenses> list=getSqlSession().selectList(Expenses.class.getName()+".getExpensesList",getExpensesDto);
        return list;
	}

    @Override
    public int getExpenseListCount(GetExpensesDto getExpensesDto) {
        int count=getSqlSession().selectOne(Expenses.class.getName()+".getExpensesListCount",getExpensesDto);
        return count;
    }

	@Override
	public void addExpenseInfo(Expenses expenses) {
		// TODO Auto-generated method stub
		getSqlSession().insert(Expenses.class.getName()+".add", expenses);
	}
	
//	@Override
//	public List<Expenses> getSystemExpenseList(){
//		List<Expenses> list=getSqlSession().selectList(Expenses.class.getName()+".getSystemExpenseList");
//        return list;
//	}
//	
//	@Override
//    public int getSystemExpenseListCount() {
//        int count=getSqlSession().selectOne(Expenses.class.getName()+".getSystemExpenseListCount");
//        return count;
//    }
	
}
