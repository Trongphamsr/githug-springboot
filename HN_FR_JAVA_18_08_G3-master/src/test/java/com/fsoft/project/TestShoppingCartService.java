package com.fsoft.project;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.impl.OrderProductDaoImpl;
import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.impl.OrderProductServiceImpl;
import com.fsoft.project.service.impl.ProductServiceImpl;
import com.fsoft.project.service.impl.ShoppingCartServiceImpl;
import com.fsoft.project.utils.constants.WebConstants;

public class TestShoppingCartService {

	private ShoppingCartServiceImpl shoppingCartService;
	private Map<String, Object> session;
	private List<Product> listProduct;
	private List<LineItem> listLineItem;
	
	@Before
	public void setUp() throws Exception {
		listProduct = new ArrayList<>();
		Product p = new Product();
		p.setId(4);
		listProduct.add(p);
		listLineItem = new ArrayList<>();
		LineItem lineItem = new LineItem();
		lineItem.setNumberProduct(8);
		lineItem.setProduct(p);
		session = new HashMap<>();
		shoppingCartService = new ShoppingCartServiceImpl(new OrderProductServiceImpl(new OrderProductDaoImpl()));
		shoppingCartService = new ShoppingCartServiceImpl(new ProductServiceImpl(new ProductDaoImpl()));
		session.put(WebConstants.LIST_LINEITEM,listProduct);
		session.put(WebConstants.LIST_PRODUCT, listLineItem);
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void removeLineItem() {
		shoppingCartService.removeLineItem(session,listProduct, listLineItem, 4, 2);
		Product p = getProduct(listProduct, 4);
		assertTrue(p != null);
		
	}
	
	@Test
	public void getAllProduct() {
		List<LineItem> items = shoppingCartService.getAllProductCart(session, listProduct);
		int expected = 0;
		int actual = items.size();
		assertTrue(expected != actual);
	}
	
	
	public static  Product getProduct(List<Product> list, int id) {
		Product p = null;
		for(Product i : list) {
			if(id == i.getId()) {
				p = i;
				break;
			}
		}
		return p;
	}
*/
}
