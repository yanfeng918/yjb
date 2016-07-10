package com.yongjinbao.member.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.beans.Principal;
import com.yongjinbao.beans.Setting;
import com.yongjinbao.beans.Setting.AccountLockType;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;
import com.yongjinbao.utils.SettingUtils;
import com.yongjinbao.utils.WebUtils;

/**
 * Controller - 会员登录
 * 
 */
@Controller("shopLoginController")
@RequestMapping("/login")
public class LoginController {



	@Resource//(name = "memberServiceImpl")
	private IMemberService memberService;
	
	@Resource(name = "areaServiceImpl")
	private IAreaService areaService;

	/**
	 * 登录提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> submit(String captchaId, String captcha, String username, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> map =new  HashMap<String,String>();
		
		String password = request.getParameter("password");
		/*if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			map.put("type", "error");
			map.put("content","验证码输入错误");
			return map;
		}*/
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			map.put("type", "error");
			map.put("content","用户名或者密码为空");
			return map;
		}
		
		Setting setting = SettingUtils.get();
		//member = memberService.getMemberByCondtion(username);
		Member member;
		//1.首先，判断用户名能够正确登录？
		member = memberService.findByUsername(username);
		//3.其次，判断手机号码能够正确登录？
		if (member == null) {
			member = memberService.findByMobile(username);
		}
		//2.其次，判断手机号是否对应多个账户
		if (member == null) {
			List<Member> list = memberService.findListByMobile(username);
			if(list.size()>1){
				map.put("type", "error");
				map.put("content","此手机号码对应多个账户");
				return map;
			}else if(list.size()<1){
				map.put("type", "error");
				map.put("content","此账号不存在");
				return map;
			}
		}
		
		
		if (member == null) {
			map.put("type", "error");
			map.put("content","该手机号未绑定！");
			return map;
		}
		if (!member.getIsEnabled()) {
			map.put("type", "error");
			map.put("content","此账号已被禁用");
			return map;
		}
		if (member.getIsLocked()) {
			if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member)) {
				int loginFailureLockTime = setting.getAccountLockTime();
				if (loginFailureLockTime == 0) {
					map.put("type", "error");
					map.put("content","此账号已被锁定");
					return map;
				}
				Date lockedDate = member.getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
				if (new Date().after(unlockDate)) {
					member.setLoginFailureCount(0);
					member.setIsLocked(false);
					member.setLockedDate(null);
					memberService.update(member);
				} else {
					map.put("type", "error");
					map.put("content","此账号已被锁定");
					return map;
					//return Message.error("shop.login.lockedAccount");
				}
			} else {
				/*member.setLoginFailureCount(0);
				member.setIsLocked(false);
				member.setLockedDate(null);
				memberService.update(member);*/
			}
		}

		if (!DigestUtils.md5Hex(password).equals(member.getPassword())) {
			int loginFailureCount = member.getLoginFailureCount() + 1;
			if (loginFailureCount >= setting.getAccountLockCount()) {
				member.setIsLocked(true);
				member.setLockedDate(new Date());
			}
			member.setLoginFailureCount(loginFailureCount);
			memberService.update(member);
			if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member)) {
				map.put("type", "error");
				map.put("content","密码错误，若连续5次密码错误账号将被锁定,10分钟后自动解除锁定！");
				return map;
				//return Message.error("shop.login.accountLockCount", setting.getAccountLockCount());
			} else {
				map.put("type", "error");
				map.put("content","用户名或密码错误");
				return map;
				//return Message.error("shop.login.incorrectCredentials");
			}
		}
		
		
		member.setLoginIp(request.getRemoteAddr());
		member.setLoginDate(new Date());
		member.setLoginFailureCount(0);
		memberService.update(member);

		// Token生成策略：MD5(id + ip + username)
		String remoteHost = request.getRemoteAddr();
		String id = member.getId().toString();
		String memberName = member.getUsername();
		String token = DigestUtils.md5Hex(remoteHost+id+memberName);
		RedisUtils.set(SerializeUtil.serialize(token), SerializeUtil.serialize(new Principal(member.getId(), memberName)));
		Long cityId = areaService.getCityByArea(member.getArea_id());
		map.put("type", "success");
		map.put("token",token);
		map.put("username",memberName);
		map.put("id", id);
		map.put("cityId", cityId.toString());
//		map.put("cityName", areaService.load(cityId).getName());
		//TODO 是否绑定
		map.put("IsActivateMobile", member.getIsActivateMobile()==null?"false":member.getIsActivateMobile().toString());
		return map;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Message logout(HttpServletRequest request, HttpServletResponse response) {
		
		String token = WebUtils.getCookie(request, Member.USERNAME_COOKIE_NAME);
        
		RedisUtils.del(SerializeUtil.serialize(token));
		
		WebUtils.removeCookie(request, response, Member.USERNAME_COOKIE_NAME);
		
		return Message.success("shop.message.success");
	}

}