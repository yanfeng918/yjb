package com.yongjinbao.finance.controller;

import com.yongjinbao.beans.Message;
import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.finance.service.IBankAccountService;
import com.yongjinbao.member.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/bankAccount/auth")
public class BankAccountController {
    @Inject
    private IBankAccountService bankAccountService;

    /**
     * 获取会员银行卡账户列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getBankList",method = RequestMethod.GET)
    @ResponseBody
    public List<BankAccount> getBankList(HttpServletRequest request){
        long member_id=bankAccountService.getMemberId(request);
        List<BankAccount> list=bankAccountService.getBankList(member_id);
        return list;
    }

    /**
     * 新增会员银行卡账户信息
     * @param bankAccount
     * @return
     */
    @RequestMapping(value = "/addBankAccount",method = RequestMethod.POST)
    @ResponseBody
    public Message addBankAccount(@ModelAttribute("bankAccount") BankAccount bankAccount,HttpServletRequest request){
        try {
            long id=bankAccountService.getMemberId(request);
            Member member=new Member();
            member.setId(id);
            bankAccount.setMember(member);
            bankAccount.setAvailable(true);
            bankAccountService.add(bankAccount);
        }catch (Exception e){
            return new Message(Message.Type.error,"添加账户错误");
        }
        return  new Message(Message.Type.success,"添加账户成功");
    }
    /**
     * 修改会员银行卡账户信息
     * @param bankAccount
     * @return
     */
    @RequestMapping(value = "/updateBankAccount",method = RequestMethod.POST)
    @ResponseBody
    public Message updateBankAccount(@ModelAttribute("bankAccount") BankAccount bankAccount){
        try {
            bankAccountService.update(bankAccount);
        }catch (Exception e){
            return new Message(Message.Type.error,"修改银行账户错误");
        }
        return  new Message(Message.Type.success,"删除完成");
    }
}
