package com.yongjinbao.finance.service;

import com.yongjinbao.finance.dao.IExpensesDao;
import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;
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
public class ExpensesServiceTest {
    @Inject
    private IExpensesService expensesService;

    @Test
    public void getExpensesList(){
        GetExpensesDto getExpensesDto=new GetExpensesDto();
//        SystemContext.setPageSize(10);
//        SystemContext.setPageOffset(1);
//        SystemContext.setOrder("desc");
//        SystemContext.setSort("e.id");
        getExpensesDto.setPageNumber(2);
        getExpensesDto.setPageSize(2);
        getExpensesDto.setMember_id(66666708l);
        Pager<Expenses> pager= expensesService.getExpensesList(getExpensesDto);
        for(Expenses expenses:pager.getList())
            System.out.println(expenses.getId());
    }
}
