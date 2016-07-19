package com.yongjinbao.member.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Principal;
import com.yongjinbao.beans.Setting;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.commons.service.ISendSMSService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;
import com.yongjinbao.utils.SettingUtils;
import com.yongjinbao.utils.WebUtils;

/**
 * Controller - 会员注册
 * 
 */
@Controller("shopRegisterController")
@RequestMapping("/register")
public class RegisterController  {


	@Resource//(name = "memberServiceImpl")
	private IMemberService memberService;
	@Resource(name = "areaServiceImpl")
	private IAreaService areaService;
	
	@Resource(name = "sendSMSServiceImpl")
	private ISendSMSService sendSMSService;

	/**
	 * 检查用户名是否被禁用或已存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (memberService.usernameExists(username)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 检查E-mail是否存在
	 */
	@RequestMapping(value = "/check_email", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		if (memberService.emailExists(email)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 注册提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> submit(@ModelAttribute("member") Member member,String inviteCode,String mobileCaptcha,String captchaId, String captcha,String area_id, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		Map<String, String> map =new  HashMap<String,String>();
		String password = request.getParameter("password");
		Setting setting = SettingUtils.get();
		if (!setting.getIsRegisterEnabled()) {
			map.put("type", "error");
			map.put("content","shop.register.disabled");
			return map;
		}
//		Long promoteId = memberService.getIdByInviteCode(inviteCode);
//		if (promoteId==null) {
//			map.put("type", "inviteCodeError");
//			map.put("content","邀请码未正常获取，请重新填写注册！");
//			return map;
//		}
		if (!memberService.findListByMobile(member.getMobile()).isEmpty()) {
			map.put("type", "error");
			map.put("content","手机号码已被注冊");
			return map;
		}
		if (member.getUsername().length() < setting.getUsernameMinLength() || member.getUsername().length() > setting.getUsernameMaxLength()) {
			map.put("type", "error");
			map.put("content","用户名过短或过长");
			return map;
		}
		if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
			map.put("type", "error");
			map.put("content","密码长度过短或过长");
			return map;
		}
		if (memberService.usernameExists(member.getUsername())||memberService.mobileExists(member.getUsername())||memberService.mobileExists(member.getEmail())) {
			map.put("type", "error");
			map.put("content","用戶名被禁用或已被注冊");
			return map;
		}
		
//		if (!setting.getIsDuplicateEmail() && memberService.emailExists(member.getEmail())) {
//			map.put("type", "error");
//			map.put("content","E-mail已被注冊");
//			return map;
//		}

//		验证手机验证码是否正确
//		SendSMS sms = new SendSMS();
//		sms.setMobile(member.getMobile());
//		sms.setType(2);
//		sms.setParam(mobileCaptcha);
//		Map<String, String> validateSMSCaptchaMap = sendSMSService.validateSMSCaptcha(sms);
//		if(!validateSMSCaptchaMap.get("type").equals("success")){
//			map.put("type", "error");
//			map.put("content",validateSMSCaptchaMap.get("content"));
//			return map;
//		}
		//添加推广人的推广ID
//        if(promoteId!=null)
//		    member.setPromoterId(promoteId);
		member.setUsername(member.getUsername().toLowerCase());
		member.setPassword(DigestUtils.md5Hex(password));
		member.setEmail(member.getEmail());
		member.setIsEnabled(true);
		member.setIsLocked(false);
		member.setLoginFailureCount(0);
		member.setLockedDate(null);
		member.setRegisterIp(request.getRemoteAddr());
		member.setLoginIp(request.getRemoteAddr());
		member.setLoginDate(new Date());
		member.setSafeKeyValue(null);
		member.setSafeKeyExpire(new Date());
        Area area=new Area();
        if(StringUtils.isNotEmpty(area_id)){
        	area.setId(Long.parseLong(area_id));
        }
        member.setArea(area);
        member.setIsActivateMobile(true);
		memberService.add(member);
        member=memberService.findByUsername(member.getUsername());
        //2015年10月14日18:20 msw 为注册用户生成邀请码
        memberService.generateInviteCode(member);
        // Token生成策略：MD5(id + ip + username)
        String remoteHost = request.getRemoteAddr();
        String id = member.getId().toString();
        String memberName = member.getUsername();
        String token = DigestUtils.md5Hex(remoteHost+id+memberName);

        RedisUtils.set(SerializeUtil.serialize(token), SerializeUtil.serialize(new Principal(member.getId(), member.getUsername())),7 * 24 * 60 * 60);

        WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME, token);
        map.put("id", id);
        map.put("type", "success");
		map.put("token",token);
		map.put("username",member.getUsername());
		Long cityId = areaService.getCityByArea(member.getArea_id());
		map.put("cityId", cityId.toString());
//		map.put("cityName", areaService.load(cityId).getName());
		return map;
	}
	
	/**
	 * 检查注册用户输入的邀请码是否有效
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/isInviteCodeEffective", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> isInviteCodeEffective(String inviteCode) {
		Map<String, Object> map =new  HashMap<String,Object>();
		try {
			Long id = memberService.getIdByInviteCode(inviteCode);
			if (id==null) {
				map.put("isExisted", "false");
				return map;
			}else {
				map.put("isExisted", "true");
				return map;
			}
		} catch (Exception e) {
			map.put("isExisted", "error");
			return map;
		}
	}
	
	
	
	
	
	
	
	

}