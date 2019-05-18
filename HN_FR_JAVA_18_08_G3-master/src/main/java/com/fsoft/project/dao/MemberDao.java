package com.fsoft.project.dao;

import com.fsoft.project.entity.Member;

public interface MemberDao {
	Member getMember(String email, String password);
	int updateMember(Member member);
}
