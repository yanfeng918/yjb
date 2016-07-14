package com.yongjinbao.finance.controller;

import com.yongjinbao.finance.dto.GetExpensesDto;
import com.yongjinbao.finance.entity.Expenses;
import com.yongjinbao.finance.service.IExpensesService;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/expenses/auth")
public class ExpensesController {
    @Inject
    private IExpensesService expensesService;

    @Inject
    private IMemberService memberService;

    /**
     * 获取用户支出列表信息
     * @param getExpensesDto
     * @return
     */
    @RequestMapping(value = "/getExpensesList")
    @ResponseBody
    public Pager<Expenses> getExpensesList(@ModelAttribute("getExpensesDto") GetExpensesDto getExpensesDto,HttpServletRequest request){
        long memberId = memberService.getMemberId(request);
        getExpensesDto.setMember_id(memberId);
        Pager<Expenses> pager = expensesService.getExpensesList(getExpensesDto);
        List<Expenses> list = pager.getList();
        Expenses[] data = list.toArray(new Expenses[list.size()]);
        pager.setList(null);
        pager.setData(data);
        return pager;
    }
}
