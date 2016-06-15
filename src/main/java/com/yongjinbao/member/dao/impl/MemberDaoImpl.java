package com.yongjinbao.member.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import com.yongjinbao.member.dao.IMemberDao;
import com.yongjinbao.member.dto.GetMyInviterFriendsDto;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl<Member,Long> implements IMemberDao {
    @Override
    public Member getAll() {
       Member member=getSqlSession().selectOne(getClz().getName()+".selectTest");
        return member;
    }

	@Override
	public Member getMemberByCondtion(String condition) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("condition", condition);
		Member member = getSqlSession().selectOne(getClz().getName()+".getMemberByCondtion", condition);
		return member;
	}

	@Override
	public boolean usernameExists(String username) {
		int count = getSqlSession().selectOne(getClz().getName()+".usernameExists", username);
		return count > 0;	
	}

	@Override
	public boolean emailExists(String email) {
		int count = getSqlSession().selectOne(getClz().getName()+".emailExists", email);
		return count > 0;	
	}
	
	@Override
	public boolean mobileExists(String email) {
		int count = getSqlSession().selectOne(getClz().getName()+".mobileExists", email);
		return count > 0;
	}

	@Override
	public Member findByUsername(String username) {
		if (username == null) {
			return null;
		}
		Member member = getSqlSession().selectOne(getClz().getName()+".findByUsername", username);
		return member;
	}
	@Override
	public Member findByMobile(String mobile) {
		if (mobile == null) {
			return null;
		}
		HashMap<String,String> map = new HashMap<>();
		map.put("mobile",mobile);
		Member member = getSqlSession().selectOne(getClz().getName()+".findByMobile", map);
		return member;
	}

	@Override
	public boolean updateBalance(UpdateBalanceDto updateBalanceDto) {
		// TODO Auto-generated method stub
		int update = getSqlSession().update(getClz().getName()+".updateBalance", updateBalanceDto);
		return update > 0;
	}

	@Override
	public Member getSystemMember() {
		return getSqlSession().selectOne(getClz().getName()+".getSystemMember");
	}
	
	@Override
	public boolean isInviteCodeExisted(String inviteCode) {
		int count = getSqlSession().selectOne(getClz().getName()+".isInviteCodeExisted", inviteCode);
		return count>0;
	}
	
	@Override
	public List<Member> getAllMember(){
		List<Member> memberList = getSqlSession().selectList(getClz().getName()+".getMemberList");
		return memberList;
	}
	
	@Override
	public void addCode(Member member, String inviteCode){
		member.setInviteCode(inviteCode);
		getSqlSession().update(getClz().getName()+".addInviteCode", member);
	}
	
	@Override
	public Long getIdByInviteCode(String inviteCode) {
		inviteCode = inviteCode.toUpperCase(Locale.ENGLISH);
		List<Long> idList = getSqlSession().selectList(getClz().getName()+".getIdByInviteCode", inviteCode);
		if (idList.size()==1) {
			return idList.get(0);
		}
		return null;
	}

	@Override
	public List<Member> getMyInviterFriends(GetMyInviterFriendsDto getMyInviterFriendsDto) {
		return getSqlSession().selectList(getClz().getName()+".getMyInviterFriends",getMyInviterFriendsDto);
	}
	@Override
	public int getMyInviterFriendsCount(GetMyInviterFriendsDto getMyInviterFriendsDto) {
		return getSqlSession().selectOne(getClz().getName()+".getMyInviterFriendsCount",getMyInviterFriendsDto);
	}

	@Override
	public List<Member> findListByEmail(String email) {
		List<Member> list = getSqlSession().selectList(getClz().getName()+".findListByEmail", email);
		return list;
	}

	@Override
	public List<Member> findListByMobile(String mobile) {
		List<Member> list = getSqlSession().selectList(getClz().getName()+".findListByMobile", mobile);
		return list;
	}

	

}
