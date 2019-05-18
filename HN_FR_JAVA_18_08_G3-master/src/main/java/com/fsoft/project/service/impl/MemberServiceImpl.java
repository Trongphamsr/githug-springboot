package com.fsoft.project.service.impl;

import com.fsoft.project.dao.MemberDao;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.MemberService;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao;
	
	public MemberServiceImpl(MemberDao dao) {
		this.memberDao = dao;
	}
	@Override
	public Member getMember(String email, String password) {
		return memberDao.getMember(email, password);
	}
	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}
}
