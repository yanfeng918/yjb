/*
 *
 */
package com.yongjinbao.commons.dao;

import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Dao - 发送短信
 * 
 */
public interface ISendSMSDao extends IBaseDao<SendSMS, Long> {

	public SendSMS getSMSbyCondition(SendSMS sms);
}