package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.impl.OrderProductDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.Member;
import com.fsoft.project.entity.OrderProduct;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.OrderProductService;
import com.fsoft.project.service.impl.OrderProductServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;

public class TestOrderService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5811453403576758268L;
	private OrderProductService orderService;
	private Member member;
	private OrderProduct order;
	private Map<String,Object> session;
	@Before
	public void setUp() throws Exception {
		orderService = new OrderProductServiceImpl(new OrderProductDaoImpl());
		session = new HashMap<>();
		member = new Member();
		order = new OrderProduct();
		session.put(Constants.CART_NUMBER, 3);
		session.put(Constants.LIST_LINE_ITEM, 3);
		session.put(WebConstants.LIST_PRODUCT, new ArrayList<Product>());
		session.put(WebConstants.LIST_LINEITEM, new ArrayList<LineItem>());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddOrder()throws Exception {
		int id = orderService.getCurrentIdentity();
		int actual;
		int expected = 1;
		order.setId(id);
		member.setId(3);
		order.setMember(member);
		session.put(Constants.MEMBER, member);
		actual = orderService.addOrderProduct(session, 123);
		assertEquals(expected, actual);
	}
	
	@Test
	public void removeListItem() {
		orderService.removeSession(session);
		assertTrue(session.get(Constants.LIST_LINE_ITEM) == null);
	}
	
	@Test
	public void removeListProduct() {
		orderService.removeSession(session);
		assertTrue(session.get(WebConstants.LIST_PRODUCT) == null);
	}
	
	@Test
	public void removeListLineItem() {
		orderService.removeSession(session);
		assertTrue(session.get(WebConstants.LIST_LINEITEM) == null);
	}
	
	@Test
	public void removeCartNumber() {
		orderService.removeSession(session);
		assertTrue(session.get(Constants.CART_NUMBER) == null);
	}
}
