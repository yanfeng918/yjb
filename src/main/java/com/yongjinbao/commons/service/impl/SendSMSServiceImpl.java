package com.yongjinbao.commons.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.yongjinbao.commons.dao.ISendSMSDao;
import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.commons.service.ISendSMSService;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Service - 地区
 */
@Service("sendSMSServiceImpl")
public class SendSMSServiceImpl extends BaseServiceImpl<SendSMS, Long> 
implements ISendSMSService {

	@Resource(name = "sendSMSDaoImpl")
	private ISendSMSDao sendSMSDao;

	@Resource(name = "sendSMSDaoImpl")
	public void setBaseDao(ISendSMSDao sendSMSDao) {
		super.setBaseDao(sendSMSDao);
	}

	@Override
	public SendSMS getSMSbyCondition(SendSMS sms) {
		//2015年12月9日17:55:47修正
		//须满足最新的验证码
		SendSMS smsResult = sendSMSDao.getSMSbyCondition(sms);
		if (smsResult!=null&&smsResult.getParam().equals(sms.getParam())) {
			return smsResult;
		}
		return null;
	}

	@Override
	public HashMap<String, String> validateSMSCaptcha(SendSMS sms) {
		//验证正确性和时效性	
		HashMap<String, String> map =new HashMap<String,String>();
  		SendSMS message = getSMSbyCondition(sms);
		if(message!=null){
			if (message.getStatus()!=null&&message.getStatus()==true) {
				//status为true时，代表已经使用/不可用
				map.put("type", "statusError");
				map.put("content","失败：验证码已失效！请重新获取！");
				return map;
			}
			Date createDate = message.getCreateDate();
			Date addMinutes = DateUtils.addMinutes(createDate, 30);
			if(addMinutes.after(new Date())){
				message.setStatus(true);
				sendSMSDao.update(message);
				map.put("type", "success");
				map.put("content","验证通过！");
				return map;
			}else{
				map.put("type", "timeoutError");
				map.put("content","失败：验证码过期！请重新获取！");
			}
		}else{
			map.put("type", "smsError");
			map.put("content","验证失败：验证码错误！");
		}
		return map;
	}

}