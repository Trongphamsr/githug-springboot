/**
 * 
 */
package com.fsoft.project.action;

import java.util.List;

import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.ProductService;
import com.fsoft.project.service.impl.ProductServiceImpl;
import com.opensymphony.xwork2.Action;

/**
 * @author hungcoutinho
 *
 */
public class SearchProductAction {

	private String val;
	private List<Product> listProduct;
	private ProductService productService;

	public String searchProductByValue() {
		productService = new ProductServiceImpl(new ProductDaoImpl());
		listProduct = productService.getListProductByValue(val);
		return Action.SUCCESS;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

}
