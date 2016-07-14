package com.yongjinbao.member.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.beans.Message.Type;
import com.yongjinbao.beans.SafeKey;
import com.yongjinbao.beans.Setting;
import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.commons.service.IMailService;
import com.yongjinbao.commons.service.ISendSMSService;
import com.yongjinbao.member.dto.GetMyInviterFriendsDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.utils.SettingUtils;
import com.yongjinbao.utils.WebUtils;

/**
 * Controller - 会员
 * 
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Resource
	private IMemberService memberService;
	
	@Resource
	private IMailService mailService;
	
	@Resource
	private ISendSMSService sendSMSService;
	
	/**
	 * 加载个人信息
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	@ResponseBody
	public String auth() {
		return null;
	}
	
	/**
	 * 加载个人信息
	 */
	@RequestMapping(value = "/auth/load", method = RequestMethod.POST)
	@ResponseBody
	public Member load(HttpServletRequest request, HttpServletResponse response) {
		Long id=memberService.getMemberId(request);
		Member member = memberService.load(id);
		return member;
	}

	/**
	 * 资料修改
	 */
	@RequestMapping(value = "/auth/modify", method = RequestMethod.POST)
	public @ResponseBody
	Message modify(Member member, HttpServletRequest request, HttpServletResponse response) {
		Long id = member.getId();
		Member load = memberService.load(id);
//		load.setEmail(member.getEmail());
		load.setName(member.getName());
//		load.setMobile(member.getMobile());
		load.setPhone(member.getPhone());
		memberService.update(load);
		return Message.success("shop.message.success");
	}
	
	
	/**
	 * 绑定邮箱
	 */
	@RequestMapping(value = "/auth/email_binding", method = RequestMethod.POST)
	@ResponseBody
	public Message emailBinding(String email,HttpServletRequest request, HttpServletResponse response) {
		
		Long id=memberService.getMemberId(request);
        Member member=memberService.load(id);
		Setting setting = SettingUtils.get();
		SafeKey safeKey = new SafeKey();
		safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
		safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(), setting.getSafeKeyExpiryTime()) : null);
		System.out.println(safeKey.getExpire());
		member.setSafeKeyExpire(safeKey.getExpire());
		member.setSafeKeyValue(safeKey.getValue());
		System.out.println(11);
		memberService.update(member);
		mailService.sendActivateUserEmail(email, member.getUsername(), safeKey);
		return Message.success("shop.message.success");
	}
	
	/**
	 * 绑定邮箱提交
	 */
	@RequestMapping(value = "/activateUserEmail", method = RequestMethod.GET)
	public @ResponseBody
	Message activateUserEmail(String username, String key) {

		Member member = memberService.findByUsername(username);
		if (member == null) {
			return Message.error("shop.message.error");
		}

		if (member.getSafeKeyValue() == null || !member.getSafeKeyValue().equals(key)) {
			return new Message(Type.error,"安全码错误，请从新发送邮件验证！");
		}
		Date ExpiredDate = member.getSafeKeyExpire();
		if (ExpiredDate != null && new Date().after(ExpiredDate)) {
			//return Message.error("shop.password.hasExpired");
			return new Message(Type.error,"该链接已失效，请从新发送邮件验证！");
		}
		//设置用户的 邮箱验证字段为true
 		member.setIsActivateEmail(true);
		member.setSafeKeyExpire(new Date());
		member.setSafeKeyValue(null);
		memberService.update(member);
		return Message.success("yjb.email.activateSuccess");
	}
	
	
	/**
	 * 检查用户是否激活邮箱
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/auth/isEmailActivated", method = RequestMethod.GET)
	@ResponseBody
	public Message isEmailActivated(HttpServletRequest request) {
		Long id=memberService.getMemberId(request);
		Member member=memberService.load(id);
		if (member == null) {
			return Message.error("shop.message.error");
		}
		if (member.getIsActivateEmail()) {
			return new Message(Type.success, member.getEmail());
		}
		return new Message(Type.error, "邮箱未绑定，请绑定！");
	}
	
	
	/**
	 * 根据会员的ID获取会员的邀请的好友
	 */
	@RequestMapping(value = "/getMyInviterFriends", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getMyInviterFriends(GetMyInviterFriendsDto getMyInviterFriendsDto,HttpServletRequest request, HttpServletResponse response) {
		Long id=memberService.getMemberId(request);
		Member member = memberService.load(id);
		getMyInviterFriendsDto.setId(id);
		Pager<Member> myInviterFriends = memberService.getMyInviterFriends(getMyInviterFriendsDto);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("inviteCode", member.getInviteCode());
		map.put("myInviterFriends", myInviterFriends);
		return map;
	}
	
	/**
	 * 根据会员的ID获取会员的邀请的好友
	 */
	@RequestMapping(value = "/getInviteCode", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getInviteCode(HttpServletRequest request) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		Member member = memberService.getCurrent(request);
		map.put("inviteCode", member.getInviteCode());
		return map;
	}
	
	/**
	 * 绑定手机号码
	 */
	@RequestMapping(value = "/mobileBinding", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String,String> mobileBinding(SendSMS sms, HttpServletRequest request) {
		sms.setType(3);
		HashMap<String, String> validateSMSCaptcha = sendSMSService.validateSMSCaptcha(sms);
		if("success".equals(validateSMSCaptcha.get("type"))){
			//TODO 把之前绑定的手机号码自动解绑
			Member oldMember = memberService.findByMobile(sms.getMobile());
			if(oldMember!=null){
				oldMember.setIsActivateMobile(false);
				memberService.update(oldMember);
			}
			Member member =memberService.findByUsername(WebUtils.getCookie(request, Member.USERNAME_NAME));
			member.setIsActivateMobile(true);
			member.setMobile(sms.getMobile());
			memberService.update(member);
		}
		return validateSMSCaptcha;
	}

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