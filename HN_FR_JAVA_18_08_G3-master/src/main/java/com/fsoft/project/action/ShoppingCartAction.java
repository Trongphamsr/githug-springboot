package com.fsoft.project.action;

import java.util.List;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.OrderProductDao;
import com.fsoft.project.dao.impl.OrderProductDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.OrderProductService;
import com.fsoft.project.service.impl.OrderProductServiceImpl;
import com.fsoft.project.service.impl.ShoppingCartServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Preparable;


public class ShoppingCartAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = 5461960535747406087L;

	private List<Product> listProduct;
	private List<LineItem> listItem;
	
	private OrderProductDao orderDao;
	private OrderProductService orderService;
	// send input editor number Product
	private int productId;
	private int number;
	private int refreshNumberCart;
	private ShoppingCartServiceImpl shoppingCartService;

	@Override
	public void prepare() throws Exception {
		orderDao = new OrderProductDaoImpl();
		orderService = new OrderProductServiceImpl(orderDao);
		shoppingCartService = new ShoppingCartServiceImpl(orderService);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		getSession().put(Constants.PAGE_INDEX, Constants.SHOPPING_CART);
		Object object = getSession().get(WebConstants.LIST_PRODUCT);
		listProduct = (List<Product>) object;
		if (listProduct == null)
			return WebConstants.SUCCESS;
		listItem = shoppingCartService.getAllProductCart(super.getSession(),listProduct);
		getSession().put(Constants.LIST_LINE_ITEM, listItem);
		return WebConstants.SUCCESS;
	}
	
	public String editNumberProduct() {
		return shoppingCartService.editLineItem(getSession(), listProduct, listItem,
				productId, number, refreshNumberCart); 
	}
	public String removeProduct() {
		return shoppingCartService.removeLineItem(getSession(), listProduct, listItem,
				productId, refreshNumberCart);
	}
	public int getRefreshNumberCart() {
		refreshNumberCart = (int) getSession().get(Constants.CART_NUMBER);
		return refreshNumberCart;
	}
	public void setRefreshNumberCart(int refreshNumberCart) {
		this.refreshNumberCart = refreshNumberCart;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
	public List<LineItem> getListItem() {
		return listItem;
	}

	public void setListItem(List<LineItem> listItem) {
		this.listItem = listItem;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	
}
