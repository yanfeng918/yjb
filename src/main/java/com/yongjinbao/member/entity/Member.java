package com.yongjinbao.member.entity;

import java.util.Date;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.interceptor.MemberInterceptor;

/**
 * Entity - 会员
 * 
 */

public class Member extends BaseEntity {

	private static final long serialVersionUID = -853745166739146789L;

	/** "身份信息"参数名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";

	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "yjb_token";
	
	/** "用户名"名称 */
	public static final String USERNAME_NAME = "yjb_username";

	/** 会员注册项值属性个数 */
	public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;

	/** 会员注册项值属性名称前缀 */
	public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";

	/** 最大收藏商品数 */
	public static final Integer MAX_FAVORITE_COUNT = 10;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** E-mail */
	private String email;

	/** 积分 */
	private Long point;


	/** 余额 */
	private float balance;

	/** 是否启用 */
	private Boolean isEnabled;

	/** 是否锁定 */
	private Boolean isLocked;

	/** 连续登录失败次数 */
	private Integer loginFailureCount;

	/** 锁定日期 */
	private Date lockedDate;

	/** 注册IP */
	private String registerIp;

	/** 最后登录IP */
	private String loginIp;

	/** 最后登录日期 */
	private Date loginDate;

	/** 姓名 */
	private String name;


	/** 出生日期 */
	private Date birth;



	/** 电话 */
	private String phone;

	/** 手机 */
	private String mobile;
	
	/** 邮箱是否验证 */
	private Boolean isActivateEmail;
	
	/** 手机是否验证 */
	private Boolean isActivateMobile;

	/** 安全密匙 */
	private String safeKeyValue;
	
	private Date safeKeyExpire; 
    /**区域**/
    private Area area;
    
    /**区域**/
    private long area_id;
    
    /**我的邀请码**/
    private String inviteCode;
    
    /**我的邀请人**/
    private long  promoterId ;
	/**
	 * 是否是员工
	 **/
	private Boolean isEmployee;

	public Boolean getIsEmployee() {
		return isEmployee;
	}


	public void setIsEmployee(Boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
    public Area getArea() {

        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Boolean getIsActivateEmail() {
		return isActivateEmail;
	}

	public void setIsActivateEmail(Boolean isActivateEmail) {
		this.isActivateEmail = isActivateEmail;
	}
	

	public Boolean getIsActivateMobile() {
		return isActivateMobile;
	}


	public void setIsActivateMobile(Boolean isActivateMobile) {
		this.isActivateMobile = isActivateMobile;
	}


	public String getSafeKeyValue() {
		return safeKeyValue;
	}

	public void setSafeKeyValue(String safeKeyValue) {
		this.safeKeyValue = safeKeyValue;
	}

	public Date getSafeKeyExpire() {
		return safeKeyExpire;
	}

	public void setSafeKeyExpire(Date safeKeyExpire) {
		this.safeKeyExpire = safeKeyExpire;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(long promoterId) {
		this.promoterId = promoterId;
	}


	public long getArea_id() {
		return area_id;
	}


	public void setArea_id(long area_id) {
		this.area_id = area_id;
	}


	public Boolean getEmployee() {
		return isEmployee;
	}

	public void setEmployee(Boolean employee) {
		isEmployee = employee;
	}

	public Boolean getEnabled() {
		return isEnabled;
	}

	public void setEnabled(Boolean enabled) {
		isEnabled = enabled;
	}

	public Boolean getLocked() {
		return isLocked;
	}

	public void setLocked(Boolean locked) {
		isLocked = locked;
	}

	public Boolean getActivateEmail() {
		return isActivateEmail;
	}

	public void setActivateEmail(Boolean activateEmail) {
		isActivateEmail = activateEmail;
	}

	public Boolean getActivateMobile() {
		return isActivateMobile;
	}

	public void setActivateMobile(Boolean activateMobile) {
		isActivateMobile = activateMobile;
	}
}