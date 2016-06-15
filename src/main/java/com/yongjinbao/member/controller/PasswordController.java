package com.yongjinbao.member.controller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yongjinbao.beans.Message;
import com.yongjinbao.beans.Message.Type;
import com.yongjinbao.beans.SafeKey;
import com.yongjinbao.beans.Setting;
import com.yongjinbao.beans.Setting.CaptchaType;
import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.commons.service.IMailService;
import com.yongjinbao.commons.service.ISendSMSService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.utils.SettingUtils;

/**
 * Controller - 会员中心 - 密码
 * 
 */
@Controller("MemberPasswordController")
@RequestMapping("/member/password")
public class PasswordController {

	@Resource//(name = "memberServiceImpl")
	private IMemberService memberService;

	@Resource//(name = "mailServiceImpl")
	private IMailService mailService;
	@Resource
	private ISendSMSService sendSMSService;

	/**
	 * 验证当前密码
	 */
	@RequestMapping(value = "auth/check_current_password", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkCurrentPassword(String currentPassword,HttpServletRequest request) {
		if (StringUtils.isEmpty(currentPassword)) {
			return false;
		}
		Member member = memberService.getCurrent(request);
		if (StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "auth/edit", method = RequestMethod.GET)
	public String edit() {
		return "shop/member/password/edit";
	}

	/**
	 * 更新密码
	 */
	@RequestMapping(value = "auth/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(String currentPassword, String password, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(currentPassword)) {
			return new Message(Type.error,"请输入原始密码和新密码");
		}

		Setting setting = SettingUtils.get();
		if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
			return new Message(Type.error,"新密码的长度不正确");
		}
		
		Long id=memberService.getMemberId(request);
        Member member=memberService.load(id);
        
		if (!StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword())) {
			return new Message(Type.error,"原始密码输入不正确");
		}
		member.setPassword(DigestUtils.md5Hex(password));
		memberService.update(member);
		return new Message(Type.success,"更新密码成功");
	}
	
	/**
	 * 找回密码
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/shop/password/find";
	}

	/**
	 * 找回密码提交(邮箱验证之后才能够发送-优化)
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public @ResponseBody
	Message find(String captchaId, String captcha, String username, String email) {

		if (/*StringUtils.isEmpty(username) ||*/ StringUtils.isEmpty(email)) {
			return Message.error("shop.common.invalid");
		}
		Member member = memberService.getMemberByCondtion(email);
		if (member == null) {
			return Message.error("shop.password.memberNotExist");
		}
		if (!member.getEmail().equalsIgnoreCase(email)) {
			return Message.error("shop.password.invalidEmail");
		}
		if (member.getIsActivateEmail()==null||!member.getIsActivateEmail()==true) {
			return Message.error("没有绑定该邮箱！");
		}
		Setting setting = SettingUtils.get();
		SafeKey safeKey = new SafeKey();
		safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
		safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(), setting.getSafeKeyExpiryTime()) : null);
		member.setSafeKeyExpire(safeKey.getExpire());
		member.setSafeKeyValue(safeKey.getValue());
		memberService.update(member);
		mailService.sendFindPasswordMail(member.getEmail(), member.getUsername(), safeKey);
		return Message.success("shop.password.mailSuccess");
	}

	/**
	 * 重置密码提交
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public @ResponseBody
	Message reset(String username, String password, String key) {

		Member member = memberService.findByUsername(username);
		if (member == null) {
			return Message.error("shop.message.error");
		}
		Setting setting = SettingUtils.get();
		if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
			return Message.warn("shop.password.invalidPassword");
		}
		if (member.getSafeKeyValue() == null || !member.getSafeKeyValue().equals(key)) {
			return new Message(Type.error,"安全码错误，请重新发送邮件验证！");
		}
		Date ExpiredDate = member.getSafeKeyExpire();
		if (ExpiredDate != null && new Date().after(ExpiredDate)) {
			return Message.error("shop.password.hasExpired");
		}
		member.setPassword(DigestUtils.md5Hex(password));
		member.setSafeKeyExpire(new Date());
		member.setSafeKeyValue(null);
		memberService.update(member);
		return Message.success("shop.password.resetSuccess");
	}
	
	/**
	 * 手机验证码重置密码提交
	 */
	@RequestMapping(value = "/resetByMobile", method = RequestMethod.POST)
	public @ResponseBody
	Message resetByMobile(String mobile, String mobileCaptcha, String password) {
		Member member = memberService.findByMobile(mobile);
		if (member == null) {
			return new Message(Type.error,"请确认您的手机号已绑定！");
		}
		Setting setting = SettingUtils.get();
		if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
			return new Message(Type.error,"新密码过短或过长！");
		}
		//验证手机的验证码
		SendSMS sms = new SendSMS();
		sms.setMobile(mobile);
		sms.setType(1);
		sms.setParam(mobileCaptcha);
		Map<String, String> validateSMSCaptcha = sendSMSService.validateSMSCaptcha(sms);
		if(!"success".equals(validateSMSCaptcha.get("type"))){
			return new Message(Type.error,validateSMSCaptcha.get("content"));
		}
		member.setPassword(DigestUtils.md5Hex(password));
		memberService.update(member);
		return new Message(Type.success,"密码重置成功！");
	}

}