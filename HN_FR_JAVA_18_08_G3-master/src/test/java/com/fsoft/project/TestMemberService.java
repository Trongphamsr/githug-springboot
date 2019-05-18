package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.impl.MemberDaoImpl;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.MemberService;
import com.fsoft.project.service.impl.MemberServiceImpl;

public class TestMemberService  {
	
	private MemberService memberService;
	private Member member;
	@Before
	public void setUp() throws Exception {
		memberService = new MemberServiceImpl(new MemberDaoImpl());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMemberNotNull() {
		member = memberService.getMember("ui@gmail.com", "12345678");	
		assertTrue(member != null);
	}
	
	@Test
	public void testMember() {
		member = memberService.getMember("xyz@gmail.com", "hello world");
		assertTrue(member == null);
	}
	
	@Test
	public void testUpdateMember() {
		int expected = 1;
		int actual;
		member = new Member();
		member.setId(1);
		member.setEmail("ngson198@gmail.com");
		member.setPassword("12345678");
		member.setPhone("0988888888");
		actual = memberService.updateMember(member);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNotUpdateMember() {
		int expected = 0;
		int actual;
		member = new Member();
		member.setId(0);
		member.setEmail("ngson198@gmail.com");
		member.setPassword("12345678");
		member.setPhone("0988888888");
		actual = memberService.updateMember(member);
		assertEquals(expected, actual);
	}
}
