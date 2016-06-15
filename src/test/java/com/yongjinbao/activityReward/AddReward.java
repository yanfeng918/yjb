package com.yongjinbao.activityReward;

import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.service.IExpensesService;
import com.yongjinbao.finance.service.IIncomeService;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.utils.activityReward.ExcelParseToList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by yanfeng on 16/1/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class AddReward {
    @Inject
    private IMemberService memberService;
    @Inject
    private IIncomeService incomeService;
    @Inject
    private IExpensesService expenseService;
    @Test
    public void test() throws Exception {
        int i= 1;
        for (Member member : ExcelParseToList.parseExcel()) {
            Member yanfeng = memberService.getMemberByCondtion(member.getUsername());
            System.out.println((i++) + "----->" + yanfeng.getUsername() + "----->" + yanfeng.getBalance()+ "----->" +member.getBalance());
            dealAddReward(yanfeng,member.getBalance());
        }

    }

    public void dealAddReward(Member member,float amount){
        Income income = new Income();
        income.setIncomeFrom(0);
        income.setAmount(amount);
        income.setMember(member);
        income.setIncomeType(Income.INCOME_TYPE.activityAward);
        incomeService.addIncomeInfo(income);

        Expenses expense = new Expenses();
        expense.setMember(memberService.getSystemMember());
        expense.setExpensesTo(member.getId());
        expense.setAmount(String.valueOf(amount));
        expense.setExpensesType(Expenses.EXPENSES_TYPE.activityReward);
        expenseService.addExpenseInfo(expense);

        UpdateBalanceDto dto = new UpdateBalanceDto();
        dto.setMember(member);
        dto.setUpdateAmount(amount, UpdateBalanceDto.INCREASE);
        memberService.updateBalance(dto);

    }
}
