package com.fsoft.project.service;

import java.util.List;
import java.util.Map;

import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.Product;

public interface ShoppingCartService {
	String removeLineItem(Map<String, Object> session, List<Product> listProduct, List<LineItem> listItem,
			int productId, int refreshNumberCart);

	String editLineItem(Map<String, Object> session, List<Product> listProduct, List<LineItem> listItem, int productId,
			int number, int refreshNumberCart);

	String addProductToSession(Map<String, Object> session, int productId, int numberProductDetail, int cartNumber,
			Map<Integer, Integer> listLineItem);
}
