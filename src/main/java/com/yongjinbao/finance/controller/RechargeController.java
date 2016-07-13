package com.yongjinbao.finance.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.BankAccount;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.finance.entity.Recharge.Recharge_STATUS;
import com.yongjinbao.finance.service.IRechargeService;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;

import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/recharge/auth")
public class RechargeController {
    @Inject
    private IRechargeService rechargeService;
    
    @Inject
    private IMemberService memberService;
    
    /**
     * 获取用户充值列表信息
     * @param getRechargeDto
     * @return
     */
    @RequestMapping(value = "/getRechargeList")
    @ResponseBody
    public Pager<Recharge> getRechargeList(GetRechargeDto getRechargeDto,HttpServletRequest request){
        Long id=rechargeService.getMemberId(request);
        getRechargeDto.setMember_id(id);
        Pager<Recharge> pager = rechargeService.getRechargeList(getRechargeDto);
        List<Recharge> list = pager.getList();
        Recharge[] data = list.toArray(new Recharge[list.size()]);
        pager.setList(null);
        pager.setData(data);
        return pager;
    }
    
    /**
     * 充值
     * @param
     * @return
     */
    @RequestMapping(value = "/addRechargetInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message addRechargetInfo(@ModelAttribute("addRechargetInfo") Recharge recharge,HttpServletRequest request){
    	try {
			Member loginMember = new Member();
			loginMember.setId(memberService.getMemberId(request));
			BankAccount bankAccount = new BankAccount();
			recharge.setStatus(Recharge_STATUS.APPLY);
			recharge.setMember(loginMember);
			recharge.setBankAccount(bankAccount);
			rechargeService.addRecharge(recharge);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
            return new Message(Message.Type.error,"添加充值信息失败！请重新填写！");
		}
        return new Message(Message.Type.success,"您的充值信息已进入待审核状态！");
    }
}
