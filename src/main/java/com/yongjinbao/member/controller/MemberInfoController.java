package com.yongjinbao.member.controller;

import com.yongjinbao.beans.Message;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fddxiaohui on 2015/8/29.
 */
@Controller("MemberInfoController")
@RequestMapping("/member/info")
public class MemberInfoController {
	
    @Resource
    private IMemberService memberService;

    @RequestMapping(value = "/getBalance",method = RequestMethod.GET)
    @ResponseBody
    public Message getBalance(HttpServletRequest request){
        Member member=new Member();
        try {
            Long id=memberService.getMemberId(request);
            member=memberService.load(id);
            return new Message(Message.Type.success,member.getBalance()+"");
        }catch (Exception e){
            return new Message(Message.Type.error,"查询余额失败");
        }
    }
}
