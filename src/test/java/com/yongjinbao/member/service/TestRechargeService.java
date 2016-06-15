package com.yongjinbao.member.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.service.IRechargeService;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.mybatis.entity.Pager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class TestRechargeService {
	
	 @Inject
	 private IRechargeService rechargeService;
	 
	 @Inject
	 private IMemberService memberService;

	@Test
	public void test() {
		
		GetRechargeDto  getRechargeDto =new GetRechargeDto();
		getRechargeDto.setPageNumber(1);
		getRechargeDto.setPageSize(1);
		getRechargeDto.setMember_id(22);
		Pager<Object> rechargeList = rechargeService.getRechargeList(getRechargeDto);
		List<Object> list = rechargeList.getList();
		System.out.println(list.size());
	}
	
//	@Test
//	public void increaseBalance(){
//		
//		UpdateBalanceDto balanceDto = new UpdateBalanceDto();
//		balanceDto.setMember(memberService.load(1L));
//		balanceDto.setUpdateAmount(14f, UpdateBalanceDto.REDUCE);
//		System.out.println(memberService.increaseBalance(balanceDto)?"余额成功添加":"添加失败！");
//	}
	
	@Test
	public void getSystem(){
		System.out.println(memberService.getSystemMember().getName());
	}

}
