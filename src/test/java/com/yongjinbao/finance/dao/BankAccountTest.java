package com.yongjinbao.finance.dao;

import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.impl.MemberServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class BankAccountTest {
    @Inject
    private IBankAccountDao bankAccountDao;
    @Inject
	private MemberServiceImpl memberService;

    @Test
    public void test() {
        for(int i=0;i<100;i++) {
            BankAccount bankAccount1 = new BankAccount();
            bankAccount1.setAccountHolder("22");
            bankAccount1.setBankAddress("22");
            bankAccount1.setAccountNum("22");
            bankAccount1.setBranchName("22");
            bankAccount1.setMember(memberService.load(1l));

            bankAccountDao.add(bankAccount1);
        }
    }
    @Test
    public void getBankList(){
        List<BankAccount> list=bankAccountDao.getBankList(1);
        for(BankAccount bankAccount:list)
            System.out.println(bankAccount.getId());
    }
    @Test
    public void updateBankAccount(){
        BankAccount bankAccount=new BankAccount();
        bankAccount.setId(Long.parseLong("3"));
        bankAccount.setAccountHolder("xiaohui");
        bankAccount.setAvailable(true);
        bankAccount.setAccountNum("100");
        bankAccount.setBranchName("xiaohui");
        bankAccount.setBankAddress("xiaohui");
        bankAccountDao.update(bankAccount);
    }
}
