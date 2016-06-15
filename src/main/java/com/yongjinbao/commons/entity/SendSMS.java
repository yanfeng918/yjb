package com.yongjinbao.commons.entity;

/**
 * Entity - 短信发送实体
 */
public class SendSMS extends BaseEntity {

	private static final long serialVersionUID = -5464197191556736967L;
	/*
	 * 短信发送的手机号
	 */
	private String mobile;
	/*
	 * 短信发送的模板、类型    1表示【忘记密码】 2表示【用户注册】 3表示【老用户绑定手机】
	 */
	private int type;
	/*
	 * 短信发送的变量
	 */
	private String param;
	
	/**
	 * 短信状态，是否可用
	 */
	private Boolean status;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}