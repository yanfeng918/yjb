package com.yongjinbao.finance.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.service.impl.IncomeService;
import com.yongjinbao.mybatis.entity.Pager;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class IncomeServiceTest {
    @Inject
    private IncomeService incomeService;

    @Test
    public void getIncomeList(){
        GetIncomeDto getIncomeDto=new GetIncomeDto();
        getIncomeDto.setPageNumber(1);
        getIncomeDto.setPageSize(10);
        getIncomeDto.setMember_id(66666708l);
        Pager<Income> pager= incomeService.getIncomeList(getIncomeDto);
        /*for(Income income:pager.getList())
            System.out.println(income.getId());*/
    }
}
