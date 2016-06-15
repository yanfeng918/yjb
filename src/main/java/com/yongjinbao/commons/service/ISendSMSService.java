/*
 * 
 * 
 * 
 */
package com.yongjinbao.commons.service;

import java.util.HashMap;

import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Service - 短信发送
 * 
 */
public interface ISendSMSService extends IBaseService<SendSMS, Long> {
	
	public SendSMS getSMSbyCondition(SendSMS sms);
	/**
	 * 验证手机验证码接口
	 */
	public HashMap<String, String> validateSMSCaptcha(SendSMS sms);
	
}