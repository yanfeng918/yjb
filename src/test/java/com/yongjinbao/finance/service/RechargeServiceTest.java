package com.yongjinbao.finance.service;

import com.yongjinbao.finance.dao.IRechargeDao;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.finance.entity.Recharge.Recharge_STATUS;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class RechargeServiceTest {
    @Inject
    private IRechargeService rechargeService;
    
    @Inject
    private IMemberService memberService;
    
    @Test
    public void getRechargeList(){
        GetRechargeDto getRechargeDto=new GetRechargeDto();
        SystemContext.setPageSize(10);
        SystemContext.setPageOffset(1);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        Pager<Recharge> pager= rechargeService.getRechargeList(getRechargeDto);
        for(Recharge recharge:pager.getList())
            System.out.println(recharge.getId());
    }
    
    @Test
    public void addRecharge(){
    	Recharge recharge = new Recharge();
    	recharge.setMember(memberService.load(1l));
//    	recharge.setAmount("10001");
    	recharge.setRechargeNumber("123422356");
    	recharge.setStatus(Recharge_STATUS.APPLY);
    	BankAccount bankAccount = new BankAccount();
    	recharge.setBankAccount(bankAccount);
    	try {
    		boolean add = rechargeService.addRecharge(recharge);
    		System.out.println((add?"添加充值记录成功!":"添加充值记录失败！"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
}
