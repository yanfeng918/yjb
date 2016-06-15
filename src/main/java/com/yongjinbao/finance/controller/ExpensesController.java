package com.yongjinbao.finance.controller;

import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.finance.service.IExpensesService;
import com.yongjinbao.mybatis.entity.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/expenses/auth")
public class ExpensesController {
    @Inject
    private IExpensesService expensesService;

    /**
     * 获取用户支出列表信息
     * @param getExpensesDto
     * @return
     */
    @RequestMapping(value = "/getExpensesList",method = RequestMethod.POST)
    @ResponseBody
    public Pager<Expenses> getExpensesList(@ModelAttribute("getExpensesDto") GetExpensesDto getExpensesDto,HttpServletRequest request){
    	getExpensesDto.setMember_id(0l);
        Pager<Expenses> pager = expensesService.getExpensesList(getExpensesDto);
        return pager;
    }
}
