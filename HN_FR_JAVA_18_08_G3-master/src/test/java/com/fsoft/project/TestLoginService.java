package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.service.impl.LoginServiceImpl;
import com.fsoft.project.utils.constants.Constants;

public class TestLoginService{
	
	private Map<String, Object> session;
	private LoginServiceImpl loginService;
	@Before
	public void setUp() throws Exception {
		session = new HashMap<>();
		loginService = new LoginServiceImpl();
		session.put(Constants.PAGE_INDEX, 1);
		session.put(Constants.PAGE_INDEX, 2);
		session.put(Constants.PAGE_INDEX, 3);
		session.put(Constants.PAGE_INDEX, 4);
		session.put(Constants.PRODUCT, 4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIndexPageMember() {
		String actual;
		String expected = "MEMBER_INDEX";
		actual = loginService.loginPage(session, 1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIndexPageProductDetail() {
		String actual;
		String expected = "MEMBER_PRODUCT_DETAIL";
		actual = loginService.loginPage(session, 2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIndexPageShoppingCart() {
		String actual;
		String expected = "MEMBER_SHOPPING_CART";
		actual = loginService.loginPage(session, 3);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIndexPageCheckOut() {
		String actual;
		String expected = "MEMBER_CHECKOUT";
		actual = loginService.loginPage(session, 4);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testIndexPageAdmin() {
		String actual;
		String expected = "SUCCESS_ADMIN";
		actual = loginService.loginPage(session, 5);
		assertEquals(expected, actual);
	}
}
