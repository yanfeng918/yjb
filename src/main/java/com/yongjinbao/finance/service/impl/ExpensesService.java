package com.yongjinbao.finance.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.finance.dao.IExpensesDao;
import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.finance.service.IExpensesService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class ExpensesService extends BaseServiceImpl<Expenses,Integer> implements IExpensesService{
	
    @Inject
    private IExpensesDao expensesDao;
    
    @Inject
    public void setBaseDao(IExpensesDao expensesDao) {
        super.setBaseDao(expensesDao);
    }

	@Override
	public <T>Pager<T> getExpensesList(GetExpensesDto getExpensesDto) {

        getExpensesDto.setPageOffset((getExpensesDto.getPageNumber()-1)*getExpensesDto.getPageSize());
        Pager<Expenses> pages=new Pager<Expenses>();
        List<Expenses> list=expensesDao.getExpensesList(getExpensesDto);
        pages.setList(list);
        pages.setPageOffset(getExpensesDto.getPageOffset());
        pages.setPageSize(getExpensesDto.getPageSize());
        int total=expensesDao.getExpenseListCount(getExpensesDto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
	}
	
//	@Override
//	public <T>Pager<T> getSystemExpensesList(){
//        getExpensesDto.setPageOffset((getExpensesDto.getPageNumber()-1)*getExpensesDto.getPageSize());
//        Pager<Expenses> pages=new Pager<Expenses>();
//        List<Expenses> list=expensesDao.getExpensesList(getExpensesDto);
//        pages.setList(list);
//        pages.setPageOffset(getExpensesDto.getPageOffset());
//        pages.setPageSize(getExpensesDto.getPageSize());
//        int total=expensesDao.getExpenseListCount(getExpensesDto);
//        pages.setTotalCount(total);
//        return (Pager<T>) pages;
//	}
//
	/**
     * 新增 【消费明细】信息
     */
    public void addExpenseInfo(Expenses expenses){
    	expensesDao.addExpenseInfo(expenses);
    }




    





}
