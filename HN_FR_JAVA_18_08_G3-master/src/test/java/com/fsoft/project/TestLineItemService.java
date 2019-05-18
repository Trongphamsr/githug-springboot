package com.fsoft.project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.impl.LineItemDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.OrderProduct;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.LineItemService;
import com.fsoft.project.service.impl.LineItemServiceImpl;

public class TestLineItemService {

	private LineItemService lineItemService;
	private LineItem lineItem;
	private Product product;
	private OrderProduct order;
	private LineItem lineItem2;
	
	@Before
	public void setUp() throws Exception {
		lineItemService =  new LineItemServiceImpl(new LineItemDaoImpl());
		lineItem = new LineItem();
		product = new Product();
		order = new OrderProduct();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddLineItem() {
		List<LineItem> items = new ArrayList<>();
		product.setPrice(123);
		product.setId(5);
		product.setColor("GRAY");
		lineItem.setNumberProduct(3);
		lineItem.setProduct(product);
		order.setId(5);
		lineItem.setOrder(order);
		lineItem2 = new LineItem(order, product, 5);
		items.add(lineItem);
		items.add(lineItem2);
		int expected = items.size();
		int actual;
		actual = lineItemService.addLineItem(items);
		assertEquals(expected, actual);
	}

	@Test
	public void testNotAddLineItem() {
		List<LineItem> items = new ArrayList<>();
		product.setPrice(123);
		product.setId(1);
		product.setColor("GRAY");
		lineItem.setNumberProduct(3);
		lineItem.setProduct(product);
		order.setId(5);
		lineItem.setOrder(order);
		items.add(lineItem);
		int expected = items.size();
		int actual;
		actual = lineItemService.addLineItem(items);
		assertTrue(actual != expected);
	}
}
