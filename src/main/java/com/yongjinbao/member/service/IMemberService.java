package com.yongjinbao.member.service;

import java.util.List;

import com.yongjinbao.member.dto.GetMyInviterFriendsDto;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import javax.servlet.http.HttpServletRequest;import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
public interface IMemberService extends IBaseService<Member,Long>{
	
	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断用户名是否禁用
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否禁用
	 */
	boolean usernameDisabled(String username);
	
	/**
	 * 判断mobile是否存在
	 * 
	 * @param mobile
	 *            mobile
	 * @return mobile是否存在
	 */
	boolean mobileExists(String mobile);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);

	/**
	 * 判断E-mail是否唯一
	 * 
	 * @param previousEmail
	 *            修改前E-mail(忽略大小写)
	 * @param currentEmail
	 *            当前E-mail(忽略大小写)
	 * @return E-mail是否唯一
	 */
	boolean emailUnique(String previousEmail, String currentEmail);

	/**
	 * 根据用户名查找会员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByUsername(String username);
	
	/**
	 * 根据手机号码查找会员
	 * 
	 * @param 
	 *            用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByMobile(String mobile);
	
	/**
	 * 根据手机号码查找会员
	 * 
	 * @param 
	 *            用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	List<Member> findListByMobile(String mobile);
	
    /**
     * 通过会员（username、mobile、email）名称获取会员对象
     * @param username
     * @return 会员
     */
     Member getMemberByCondtion(String username);

	/**
	 * 根据E-mail查找会员
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	List<Member> findListByEmail(String email);

	/**
	 * 判断会员是否登录
	 * 
	 * @return 会员是否登录
	 */
	//boolean isAuthenticated();

	/**
	 * 获取当前登录会员
	 * 
	 * @return 当前登录会员，若不存在则返回null
	 */
	Member getCurrent(HttpServletRequest request);

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名，若不存在则返回null
	 */
	//String getCurrentUsername();
	
	/**
	 * 更改用户余额
	 * @param increaseBalanceDto
	 * @return
	 */
	boolean updateBalance(UpdateBalanceDto updateBalanceDto);
	
	/**
     * 获取系统管理员账号
     * @return
     */
    public Member getSystemMember();
    
    /**
     * 注册用户生成唯一邀请码
     * @return
     */
    public String generateInviteCode();
    
    /**根据邀请码获取用户ID 【2015年10月14 msw】**/
    public Long getIdByInviteCode(String inviteCode); 
    
    /** 为新注册的用户添加新的邀请码【2015年10月14日 msw】**/
    public void generateInviteCode(Member member);
    
    /**
     * 根据会员的ID获取会员的邀请的好友
     * @return
     */
    public Pager<Member> getMyInviterFriends(GetMyInviterFriendsDto getMyInviterFriendsDto);
}
