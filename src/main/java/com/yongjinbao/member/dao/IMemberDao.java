package com.yongjinbao.member.dao;

import java.util.List;

import com.yongjinbao.member.dto.GetMyInviterFriendsDto;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
public interface IMemberDao extends IBaseDao<Member,Long> {
	
     Member getAll();
    
    /**
     * 通过会员（username、mobile、email）名称获取会员对象
     * @param username
     * @return 会员
     */
     Member getMemberByCondtion(String username);
    
    
	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);
	/**
	 * 判断mobile是否存在
	 * 
	 * @param mobile
	 *            mobile
	 * @return mobile是否存在
	 */
	boolean mobileExists(String mobile);

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
	
	public List<Member> findListByEmail(String email);
	
	/**
	 * 添加用户余额
	 * @param increaseBalanceDto
	 * @return
	 */
	boolean updateBalance(UpdateBalanceDto updateBalanceDto);
	
	/**
     * 获取系统管理员账号
     * @return
     */
    public Member getSystemMember();
    
    /**2015年10月14日 masw 生成的邀请码是否已经存在 **/
    public boolean isInviteCodeExisted(String inviteCode);
    
    public List<Member> getAllMember();
    
    public void addCode(Member member, String inviteCode);
    
    /**根据邀请码获取用户ID **/
    public Long getIdByInviteCode(String inviteCode);

    
    
    /**
     * 根据会员的ID获取会员的邀请的好友
     * @return
     */
    public List<Member> getMyInviterFriends(GetMyInviterFriendsDto getMyInviterFriendsDto);
    
    
    /**
     * 根据会员的ID获取会员的邀请的好友数量
     * @return
     */
    public int getMyInviterFriendsCount(GetMyInviterFriendsDto getMyInviterFriendsDto);
    
}
