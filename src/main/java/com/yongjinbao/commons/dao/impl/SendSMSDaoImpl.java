package com.yongjinbao.commons.dao.impl;

import org.springframework.stereotype.Repository;

import com.yongjinbao.commons.dao.ISendSMSDao;
import com.yongjinbao.commons.entity.SendSMS;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Dao - 短信
 */
@Repository("sendSMSDaoImpl")
public class SendSMSDaoImpl extends BaseDaoImpl<SendSMS, Long> implements ISendSMSDao {

	@Override
	public SendSMS getSMSbyCondition(SendSMS sms) {
		SendSMS sendSMS=getSqlSession().selectOne(SendSMS.class.getName()+".getSMSbyCondition",sms);
		return sendSMS;
	}

}