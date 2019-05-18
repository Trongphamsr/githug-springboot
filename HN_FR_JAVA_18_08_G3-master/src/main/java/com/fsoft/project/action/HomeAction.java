package com.fsoft.project.action;

import java.util.List;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.impl.HomeServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

/**
 * @author hungcoutinho
 *
 */
public class HomeAction extends BaseAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3391172537723552251L;
	private HomeServiceImpl homeService;

	@Override
	public void prepare() {
		homeService = new HomeServiceImpl();
	}

	public String execute() {
		return homeService.execute(getSession());
	}

	public String listProduct() {
		return homeService.listProduct();
	}

	public String addProductToSession() {
		return Action.SUCCESS;
	}

	public String listProductByValue() {
		return homeService.listProductByValue(getSession());
	}

	public String loadListProductByValue() {
		return homeService.loadListProductByValue();
	}

	public List<Product> getListNewProduct() {
		return homeService.getListNewProduct();
	}

	public void setListNewProduct(List<Product> listNewProduct) {
		homeService.setListNewProduct(listNewProduct);
	}

	public List<Product> getListProduct() {
		return homeService.getListProduct();
	}

	public void setListProduct(List<Product> listProduct) {
		homeService.setListProduct(listProduct);
	}

	public int getTotalPages() {
		return homeService.getTotalPages();
	}

	public void setTotalPages(int totalPages) {
		homeService.setTotalPages(totalPages);
	}

	public int getCurrentPage() {
		return homeService.getCurrentPage();
	}

	public void setCurrentPage(int currentPage) {
		homeService.setCurrentPage(currentPage);
	}

	public int getProductId() {
		return homeService.getProductId();
	}

	public void setProductId(int productId) {
		homeService.setProductId(productId);
	}

	public String getCategoryId() {
		return homeService.getCategoryId();
	}

	public void setCategoryId(String categoryId) {
		homeService.setCategoryId(categoryId);
	}

	public String getSortName() {
		return homeService.getSortName();
	}

	public void setSortName(String sortName) {
		homeService.setSortName(sortName);
	}

	public String getSortPrice() {
		return homeService.getSortPrice();
	}

	public void setSortPrice(String sortPrice) {
		homeService.setSortPrice(sortPrice);
	}

	public String getPriceMin() {
		return homeService.getPriceMin();
	}

	public void setPriceMin(String priceMin) {
		homeService.setPriceMin(priceMin);
	}

	public String getPriceMax() {
		return homeService.getPriceMax();
	}

	public void setPriceMax(String priceMax) {
		homeService.setPriceMax(priceMax);
	}

	public String getUrl() {
		return homeService.getUrl();
	}

	public void setUrl(String url) {
		homeService.setUrl(url);
	}

	public List<Category> getListCategory() {
		return homeService.getListCategory();
	}

	public void setListCategory(List<Category> listCategory) {
		homeService.setListCategory(listCategory);
	}
	
}
