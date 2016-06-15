package com.yongjinbao.finance.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.dto.GetIncomeExpenseDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.service.IIncomeService;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.finance.vo.IncomeExpenseVO;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/income/auth")
public class IncomeController {
	@Inject
	private IIncomeService incomeService;

	@Inject
	private IMemberService memberService;

	/**
	 * 获取用户收入列表信息
	 * @param getIncomeDto
	 * @return
	 */
	@RequestMapping(value = "/getIncomeList",method = RequestMethod.POST)
	@ResponseBody
	public Pager<Income> getIncomeList(@ModelAttribute("getIncomeDto") GetIncomeDto getIncomeDto,HttpServletRequest request){
		getIncomeDto.setMember_id(incomeService.getMemberId(request));
		Pager<Income> pager = incomeService.getIncomeList(getIncomeDto);
		return pager;
	}

	@RequestMapping(value = "/getIncomeExpenseList",method = RequestMethod.POST)
	@ResponseBody
	public Pager<IncomeExpenseVO> getIncomeExpenseList(@ModelAttribute("getIncomeDto") GetIncomeExpenseDto getIncomeExpenseDto,HttpServletRequest request){
		getIncomeExpenseDto.setMember_id(incomeService.getMemberId(request));
		Pager<IncomeExpenseVO> pager = incomeService.getMyIncomeExpenseList(getIncomeExpenseDto);
		return pager;
	}

	@RequestMapping(value = "/getIncomeAmount",method = RequestMethod.GET)
	@ResponseBody
	public IncomeAmountVO getIncomeAmount(HttpServletRequest request){
		IncomeAmountVO vo = incomeService.getIncomeAmountByDate(memberService.getMemberId(request));
		vo.setBalance(memberService.getCurrent(request).getBalance());
		return vo;
	}
	
	@RequestMapping(value = "/getFrozenAmount",method = RequestMethod.GET)
	@ResponseBody
	public float getFrozenAmount(HttpServletRequest request){
		IncomeAmountVO vo = incomeService.getIncomeAmountByDate(memberService.getMemberId(request));
		vo.setBalance(memberService.getCurrent(request).getBalance());
		if (vo.getYesterdaySum()>vo.getBalance()) {
			vo.setYesterdaySum(vo.getBalance());
		}
		return vo.getYesterdaySum();
	}

}
