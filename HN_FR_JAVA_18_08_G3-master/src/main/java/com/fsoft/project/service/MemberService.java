package com.fsoft.project.service;

import java.util.Map;

import com.fsoft.project.entity.Member;

public interface MemberService {
	Member getMember(String email, String password);
	int updateMember(Member member);
}
