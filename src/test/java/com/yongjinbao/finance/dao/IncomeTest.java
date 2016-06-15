package com.yongjinbao.finance.dao;

import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.member.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class IncomeTest {
    @Inject
    private IIncomeDao incomeDao;
    @Test
    public void getIncomeList(){
        GetIncomeDto getIncomeDto=new GetIncomeDto();
        getIncomeDto.setMember_id(1);
        incomeDao.getIncomeList(getIncomeDto);
    }
    @Test
    public void addIncome(){
        Income income=new Income();
        Member member=new Member();
        income.setMember(member);
        income.setAmount(100);
//        income.setIncomeType(1);
        incomeDao.add(income);
    }
}
