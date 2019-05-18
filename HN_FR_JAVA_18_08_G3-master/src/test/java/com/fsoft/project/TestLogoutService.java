package com.fsoft.project;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.entity.Member;
import com.fsoft.project.service.LogoutService;
import com.fsoft.project.service.impl.LogoutServiceImpl;
import com.fsoft.project.utils.constants.Constants;

public class TestLogoutService {

	private LogoutService logoutService;
	private Member member;
	private Map<String, Object> session;
	@Before
	public void setUp() throws Exception {
		logoutService = new LogoutServiceImpl();
		session = new HashMap<>();
		member = new Member();
		member.setId(3);
		session.put(Constants.MEMBER, member);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRemoveMember() {
		int expected = 0;
		logoutService.removeMember(session);
		int actual = session.size();
		assertEquals(expected, actual);
	}

}
