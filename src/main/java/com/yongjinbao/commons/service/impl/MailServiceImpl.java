package com.yongjinbao.commons.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.yongjinbao.beans.SafeKey;
import com.yongjinbao.beans.Setting;
import com.yongjinbao.commons.service.IMailService;
import com.yongjinbao.commons.service.ITemplateService;
import com.yongjinbao.utils.SettingUtils;
import com.yongjinbao.utils.SpringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Service - 邮件
 */
@Service("mailServiceImpl")
public class MailServiceImpl implements IMailService {

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource(name = "javaMailSender")
	private JavaMailSenderImpl javaMailSender;
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	@Resource(name = "templateServiceImpl")
	private ITemplateService templateService;
	
	/*@Resource(name = "springUtils")
	private SpringUtils springUtils;*/

	/**
	 * 添加邮件发送任务
	 * @param mimeMessage MimeMessage
	 */
	private void addSendTask(final MimeMessage mimeMessage) {
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					javaMailSender.send(mimeMessage);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void send(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail, String subject, String templatePath, Map<String, Object> model, boolean async) {
		Assert.hasText(smtpFromMail);
		Assert.hasText(smtpHost);
		Assert.notNull(smtpPort);
		Assert.hasText(smtpUsername);
		Assert.hasText(smtpPassword);
		Assert.hasText(toMail);
		Assert.hasText(subject);
		Assert.hasText(templatePath);
		try {
			Setting setting = SettingUtils.get();
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate(templatePath);
			String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			javaMailSender.setHost(smtpHost);
			javaMailSender.setPort(smtpPort);
			javaMailSender.setUsername(smtpUsername);
			javaMailSender.setPassword(smtpPassword);
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			mimeMessageHelper.setFrom(MimeUtility.encodeWord(setting.getSiteName()) + " <" + smtpFromMail + ">");
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(toMail);
			mimeMessageHelper.setText(text, true);
			if (async) {
				addSendTask(mimeMessage);
			} else {
				javaMailSender.send(mimeMessage);
			}
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void send(String toMail, String subject, String templatePath, Map<String, Object> model, boolean async) {
		Setting setting = SettingUtils.get();
		send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, model, async);
	}
	@Override
	public void send(String toMail, String subject, String templatePath, Map<String, Object> model) {
		Setting setting = SettingUtils.get();
		send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, model, true);
	}
	@Override
	public void send(String toMail, String subject, String templatePath) {
		Setting setting = SettingUtils.get();
		send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, null, true);
	}
	@Override
	public void sendTestMail(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail) {
		Setting setting = SettingUtils.get();
		String subject = SpringUtils.getMessage("admin.setting.testMailSubject", setting.getSiteName());
		com.yongjinbao.beans.Template testMailTemplate = templateService.get("testMail");
		
		send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, subject, testMailTemplate.getTemplatePath(), null, false);
	}
	
	@Override
	public void sendFindPasswordMail(String toMail, String username, SafeKey safeKey) {
		Setting setting = SettingUtils.get();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", username);
		model.put("safeKey", safeKey);
		String subject = SpringUtils.getMessage("shop.password.mailSubject", setting.getSiteName());
		com.yongjinbao.beans.Template findPasswordMailTemplate = templateService.get("findPasswordMail");
		send(toMail, subject, findPasswordMailTemplate.getTemplatePath(), model);
	}
	
	@Override
	public void sendActivateUserEmail(String toMail, String username, SafeKey safeKey) {
		Setting setting = SettingUtils.get();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", username);
		model.put("safeKey", safeKey);
		String subject = SpringUtils.getMessage("shop.password.mailSubject", setting.getSiteName());
		com.yongjinbao.beans.Template findPasswordMailTemplate = templateService.get("emailBinding");
		send(toMail, "请点此链接完成邮件验证", findPasswordMailTemplate.getTemplatePath(), model);
	}

}