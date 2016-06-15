package com.yongjinbao.finance.dao;

import com.yongjinbao.finance.dto.GetExpensesDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by fddxiaohui on 2015/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class ExpensesTest {
    @Inject
    private IExpensesDao expensesDao;

    @Test
    public void getExpenses(){
        GetExpensesDto getExpensesDTO=new GetExpensesDto();
        getExpensesDTO.setBeginDate("14-08-18 15:59:57");
        getExpensesDTO.setEndDate("15-08-18 15:59:58");
        expensesDao.getExpensesList(getExpensesDTO);
    }
}
