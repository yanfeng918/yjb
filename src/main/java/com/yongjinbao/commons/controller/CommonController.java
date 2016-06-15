package com.yongjinbao.commons.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.beans.Setting;
import com.yongjinbao.beans.Setting.CaptchaType;
import com.yongjinbao.beans.Message.Type;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.commons.service.ISendSMSService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.utils.CommonUtils;
import com.yongjinbao.utils.SettingUtils;

/**
 * Controller - 共用
 */
@Controller("shopCommonController")
@RequestMapping("/common")
public class CommonController {


	@Resource(name = "areaServiceImpl")
	private IAreaService areaService;
	@Resource(name = "sendSMSServiceImpl")
	private ISendSMSService sendSMSService;
	
	@Resource
	private IMemberService memberService;

	/**
	 * 网站关闭
	 */
	@RequestMapping("/site_close")
	public String siteClose() {
		Setting setting = SettingUtils.get();
		if (setting.getIsSiteEnabled()) {
			return "redirect:/";
		} else {
			return "/shop/common/site_close";
		}
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			//需要sessionId 来验证校验码
			captchaId = request.getSession().getId();
		}
		String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
		String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
		response.addHeader(pragma, value);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
//			servletOutputStream = response.getOutputStream();
//			BufferedImage bufferedImage = captchaService.buildImage(captchaId);
//			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
//			servletOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}

	/**
	 * 错误提示
	 */
	@RequestMapping("/error")
	@ResponseBody
	public String error(String message) {
		return message;
	}

	/**
	 * 资源不存在
	 */
	@RequestMapping("/resource_not_found")
	public String resourceNotFound() {
		return "/shop/common/resource_not_found";
	}
	
	/**
	 * 地区
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody
	Map<Long, String> area(Long parentId) {
		List<Area> areas = new ArrayList<Area>();
		Area parent = areaService.load(parentId);
		if (parent != null) {
			areas = new ArrayList<Area>(areaService.findChildren(parentId));
		} else {
			areas = areaService.findRoots();
		}
		Map<Long, String> options = new HashMap<Long, String>();
		for (Area area : areas) {
			options.put(area.getId(), area.getName());
		}
		return options;
	}
	
	
	/**
	 * 根据区域的id获取区域所在的城市
	 */
	@RequestMapping(value = "/getCityByArea", method = RequestMethod.GET)
	public @ResponseBody
	Long getCityByArea(Long areaId) {
		Long area_id=null;
		Area area = areaService.load(areaId);
		if(area==null){
			return 0L;
		}
		String[] areas = area.getTreePath().split(",");
		if(areas.length==0){
			area_id=area.getId();
		}else{
			area_id=Long.parseLong(areas[1]);
		}
		return area_id;
	}
	
	/**
	 * 发送手机验证码接口
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sendSMSCaptcha", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> sendSMSCaptcha(SendSMS sms,String captchaId, String captcha) {
		//发送验证码的接口；（验证手机号码格式、手机号码是否注册、生成验证码、发送短信、保存短信记录）
		
		Map<String, Object> map =new  HashMap<String,Object>();

		
		String mobile = sms.getMobile();
		if(!mobile.isEmpty()&&CommonUtils.isMobile(mobile)){
			//表示忘记密码
			if(sms.getType()==1){
				Member member = memberService.findByMobile(mobile);
				if (member == null) {
					map.put("type", "smsSendErr");
					map.put("content","手机号未注册或未绑定！");
					return map;
				}
			}
			//表示注册
			if(sms.getType()==2){
				Member member = memberService.findByMobile(mobile);
				if (member != null) {
					map.put("type", "smsSendErr");
					map.put("content","手机号码已经注册！");
					return map;
				}
			}
			
			String smsCaptcha = CommonUtils.generateSMSCaptcha();
			boolean smsSendStatus = CommonUtils.sendSMS(mobile, sms.getType(), smsCaptcha);
			
			if (smsSendStatus) {
				sms.setParam(smsCaptcha);
				sendSMSService.add(sms);
				map.put("type", "smsSendSuccess");
				//表示登录后的绑定
				if(sms.getType()==3){
					Member member = memberService.findByMobile(mobile);
					if (member != null) {
						map.put("type", "smsSendErr");
						map.put("content","手机号码已被绑定,短信发送成功！");
						return map;
					}
				}else{
					map.put("content","短信发送成功！");
				}
				
				
			}else{
				map.put("type", "smsSendErr");
				map.put("content","短信发送失败！");
			}
		}else {
			map.put("type", "mobileErr");
			map.put("content","手机号码格式错误！");
		}
		return map;
	}
	
	
	/**
	 * 验证手机验证码接口
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/validateSMSCaptcha", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> validateSMSCaptcha(SendSMS sms) {
		//验证可用性、正确性和时效性	
		Map<String, Object> map =new HashMap<String,Object>();
  		SendSMS message = sendSMSService.getSMSbyCondition(sms);
		if(message!=null){
			Date createDate = message.getCreateDate();
			Date addMinutes = DateUtils.addMinutes(createDate, 30);
			if(addMinutes.after(new Date())){
				map.put("type", "success");
				map.put("content","验证通过！");
				return map;
			}else{
				map.put("type", "timeoutError");
				map.put("content","验证失败：验证码过期！");
			}
		}else{
			map.put("type", "smsError");
			map.put("content","验证失败：验证码错误！");
		}
		return map;
	}

}