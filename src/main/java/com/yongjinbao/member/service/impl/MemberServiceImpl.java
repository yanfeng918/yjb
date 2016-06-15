package com.yongjinbao.member.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yongjinbao.member.dao.IMemberDao;
import com.yongjinbao.member.dto.GetMyInviterFriendsDto;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member,Long> implements IMemberService{

	@Inject
	private IMemberDao memberDao;

	@Inject
	public void setBaseDao(IMemberDao memberDao) {
		super.setBaseDao(memberDao);
	}

	@Override
	public boolean usernameExists(String username) {
		return memberDao.usernameExists(username);
	}
	@Override
	public boolean usernameDisabled(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mobileExists(String mobile) {
		return memberDao.mobileExists(mobile);
	}

	@Override
	public boolean emailExists(String email) {
		return memberDao.emailExists(email);
	}

	@Override
	public boolean emailUnique(String previousEmail, String currentEmail) {
		if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
			return true;
		} else {
			if (memberDao.emailExists(currentEmail)) {
				return false;
			} else {
				return true;
			}
		}
	}
	@Override
	public Member findByUsername(String username) {
		return memberDao.findByUsername(username);
	}

	@Override
	public List<Member> findListByEmail(String email) {
		return memberDao.findListByEmail(email);
	}

	@Override
	public Member getCurrent(HttpServletRequest request) {
		long memberId = getMemberId(request);		
		return memberDao.load((long)memberId);
	}


	@Override
	public boolean updateBalance(UpdateBalanceDto updateBalanceDto) {
		return memberDao.updateBalance(updateBalanceDto);
	}

	@Override
	public Member getSystemMember() {
		return memberDao.getSystemMember();
	}

	@Override
	public Member getMemberByCondtion(String username) {
		return memberDao.getMemberByCondtion(username);
	}

	@Override
	public String generateInviteCode() {
		boolean isExisted = true;
		String inviteCode="";
		while(isExisted){
			inviteCode = getInviteCode();
			isExisted = memberDao.isInviteCodeExisted(inviteCode);
		}
		return inviteCode;
	}
	//生成邀请码
	private String getInviteCode(){
		String a = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
		StringBuffer codeBuffer = new StringBuffer();
		for (int i = 0; i < 6; i++){ 
			int rand = (int) (Math.random() * a.length()); 
			codeBuffer.append(a.charAt(rand));
		} 
		return codeBuffer.toString();
	}

	@Override
	public Long getIdByInviteCode(String inviteCode) {
		return memberDao.getIdByInviteCode(inviteCode);
	}

	@Override
	public void generateInviteCode(Member member) {
		String code = generateInviteCode();
		memberDao.addCode(member, code);
	}

	@Override
	public Pager<Member> getMyInviterFriends(GetMyInviterFriendsDto getMyInviterFriendsDto) {
		getMyInviterFriendsDto.setPageOffset((getMyInviterFriendsDto.getPageNumber()-1)*getMyInviterFriendsDto.getPageSize());
	        Pager<Member> pager=new Pager<Member>();
	        List<Member> list=memberDao.getMyInviterFriends(getMyInviterFriendsDto);
	        pager.setList(list);
	        pager.setPageOffset(getMyInviterFriendsDto.getPageOffset());
	        pager.setPageSize(getMyInviterFriendsDto.getPageSize());
	        int total=memberDao.getMyInviterFriendsCount(getMyInviterFriendsDto);
	        pager.setTotalCount(total);
	        return pager;
	}

	@Override
	public Member findByMobile(String mobile) {
		return memberDao.findByMobile(mobile);
	}

	@Override
	public List<Member> findListByMobile(String mobile) {
		return memberDao.findListByMobile(mobile);
	}

}
