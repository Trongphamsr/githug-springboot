/**
 * 
 */
package com.fsoft.project.action;

import java.util.Map;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.service.ProductService;
import com.fsoft.project.service.impl.ProductServiceImpl;
import com.fsoft.project.service.impl.ShoppingCartServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

/**
 * @author hungcoutinho
 *
 */
public class SessionAction extends BaseAction implements Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3930995445097362624L;
	private int productId;
	private ProductService productService;
	private boolean addProduct = false;
	private Map<Integer, Integer> listLineItem;
	private int cartNumber;
	private int numberProductDetail;
	private ShoppingCartServiceImpl shoppingCartService;
	
	@Override
	public void prepare() throws Exception {
		productService = new ProductServiceImpl(new ProductDaoImpl());
		shoppingCartService = new ShoppingCartServiceImpl(productService);
	}

	public String addProductToSession() {
		return shoppingCartService.addProductToSession(getSession(), productId,
				numberProductDetail, cartNumber, listLineItem);
	}

	public int getNumberProductDetail() {
		
		return numberProductDetail;
	}

	public void setNumberProductDetail(int numberProductDetail) {
		
		this.numberProductDetail = numberProductDetail;
	}

	public String getNumberProduct() {
		Object object = getSession().get(Constants.CART_NUMBER);
		if (object != null)
			cartNumber = (int) object;
		return Action.SUCCESS;
	}

	public int getCartNumber() {
		cartNumber = (int) getSession().get(Constants.CART_NUMBER);
		return cartNumber;
	}

	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}

	public boolean getAddProduct() {
		addProduct = shoppingCartService.isMessage();
		return addProduct;
	}

	public void setAddProduct(boolean addProduct) {
		this.addProduct = addProduct;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
