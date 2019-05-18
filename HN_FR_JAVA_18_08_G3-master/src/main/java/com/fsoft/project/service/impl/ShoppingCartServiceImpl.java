package com.fsoft.project.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.OrderProduct;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.OrderProductService;
import com.fsoft.project.service.ProductService;
import com.fsoft.project.service.ShoppingCartService;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Action;

public class ShoppingCartServiceImpl implements ShoppingCartService {

	private OrderProductService orderService;
	private ProductService productService;
	private boolean message = false;

	public ShoppingCartServiceImpl(OrderProductService orderService) {
		this.orderService = orderService;
	}

	public ShoppingCartServiceImpl(ProductService productService) {
		this.productService = productService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String removeLineItem(Map<String, Object> session, List<Product> listProduct, List<LineItem> listItem,
			int productId, int refreshNumberCart) {
		Map<Integer, Integer> numberProduct = (Map<Integer, Integer>) session.get(WebConstants.LIST_LINEITEM);
		refreshNumberCart = (int) session.get(Constants.CART_NUMBER);
		refreshNumberCart -= numberProduct.get(productId);
		numberProduct.remove(productId);
		listProduct = (List<Product>) session.get(WebConstants.LIST_PRODUCT);
		for (int i = 0; i < listProduct.size(); i++) {
			if (listProduct.get(i).getId() == productId) {
				listProduct.remove(i);
				break;
			}
		}
		session.put(WebConstants.LIST_LINEITEM, numberProduct);
		session.put(WebConstants.LIST_PRODUCT, listProduct);
		session.put(Constants.CART_NUMBER, refreshNumberCart);
		listItem = getAllProductCart(session, listProduct);
		session.put(Constants.LIST_LINE_ITEM, listItem);
		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public List<LineItem> getAllProductCart(Map<String, Object> session, List<Product> listProduct) {
		List<LineItem> list = new ArrayList<>();
		LineItem lineItem = null;
		OrderProduct orderProduct = new OrderProduct();
		try {
			orderProduct.setId(orderService.getCurrentIdentity() + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<Integer, Integer> numberProduct = (Map<Integer, Integer>) session.get(WebConstants.LIST_LINEITEM);
		Set<Integer> keySet = numberProduct.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			int key = iterator.next();
			lineItem = new LineItem();
			lineItem.setOrder(orderProduct);
			lineItem.setProduct(getProduct(key, listProduct));
			lineItem.setNumberProduct(numberProduct.get(key));
			list.add(lineItem);
		}
		return list;
	}

	private Product getProduct(int id, List<Product> listProduct) {
		Product product = null;
		for (Product i : listProduct) {
			if (i.getId() == id) {
				product = i;
				break;
			}
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String editLineItem(Map<String, Object> session, List<Product> listProduct, List<LineItem> listItem,
			int productId, int number, int refreshNumberCart) {
		Map<Integer, Integer> numberProduct = (Map<Integer, Integer>) session.get(WebConstants.LIST_LINEITEM);
		numberProduct.put(productId, number);
		int sum = 0;
		Set<Integer> keySet = numberProduct.keySet();
		Iterator<Integer> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			sum += numberProduct.get(iterator.next());
		}
		refreshNumberCart = sum;
		session.put(Constants.CART_NUMBER, refreshNumberCart);
		listProduct = (List<Product>) session.get(WebConstants.LIST_PRODUCT);
		listItem = getAllProductCart(session, listProduct);
		session.put(Constants.LIST_LINE_ITEM, listItem);
		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String addProductToSession(Map<String, Object> session, int productId, int numberProductDetail,
			int cartNumber, Map<Integer, Integer> listLineItem) {
		List<Product> listP = (List<Product>) session.get(WebConstants.LIST_PRODUCT);
		if (listP == null) {
			listP = new LinkedList<>();
		}
		productService = new ProductServiceImpl(new ProductDaoImpl());
		Product product = productService.getProductById(productId);
		if (product != null) {
			listP.add(product);
			message = true;
			session.put(WebConstants.LIST_PRODUCT, listP);
		}

		listLineItem = (Map<Integer, Integer>) session.get(WebConstants.LIST_LINEITEM);
		if (listLineItem == null) {
			listLineItem = new HashMap<>();
		}

		for (int i = 0; i < listP.size(); i++) {
			if (numberProductDetail == 0) {
				int number = 1;
				if (listLineItem.get(listP.get(i).getId()) == null) {
					listLineItem.put(listP.get(i).getId(), number);
				}
				for (int j = i + 1; j < listP.size(); j++) {
					if (listP.get(i).getId() == listP.get(j).getId()) {
						listP.remove(j);
						number = listLineItem.get(listP.get(i).getId());
						listLineItem.put(listP.get(i).getId(), ++number);
					}
				}
			} else {
				if (listLineItem.get(listP.get(i).getId()) == null) {
					listLineItem.put(listP.get(i).getId(), numberProductDetail);
				}
				for (int j = i + 1; j < listP.size(); j++) {
					int number;
					if (listP.get(i).getId() == listP.get(j).getId()) {
						listP.remove(j);
						number = listLineItem.get(listP.get(i).getId());
						number += numberProductDetail;
						listLineItem.put(listP.get(i).getId(), number);
					}
				}
			}
		}
		session.put(WebConstants.LIST_LINEITEM, listLineItem);
		if (numberProductDetail == 0)
			session.put(Constants.CART_NUMBER, ++cartNumber);
		else {
			int numberProduct = (int) session.get(Constants.CART_NUMBER);
			cartNumber = numberProduct + numberProductDetail;
			session.put(Constants.CART_NUMBER, cartNumber);
		}
		return Action.SUCCESS;
	}

	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}
}
