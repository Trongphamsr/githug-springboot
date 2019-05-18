/**
 * 
 */
package com.fsoft.project.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.CategoryService;
import com.fsoft.project.service.HomeService;
import com.fsoft.project.service.ProductService;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Action;

/**
 * @author Nguyen Son
 *
 */
public class HomeServiceImpl implements HomeService {

	private List<Product> listNewProduct;
	private List<Product> listProduct;
	private List<Category> listCategory;
	private int totalPages;
	private int currentPage;
	private int productId;
	private String categoryId = WebConstants.CATEGORY_ALL;
	private String sortName = WebConstants.DEFAULT;
	private String sortPrice = WebConstants.DEFAULT;
	private String priceMin = WebConstants.PRICE_MIN;
	private String priceMax = WebConstants.PRICE_MAX;
	private String url;
	private ProductService productService = new ProductServiceImpl(new ProductDaoImpl());
	private CategoryService categoryService = new CategoryServiceImpl(new CategoryDaoImpl());

	@Override
	public String execute(Map<String, Object> session) {
		// TODO Auto-generated method stub
		session.put(Constants.PAGE_INDEX, Constants.INDEX);
		listNewProduct = productService.getListNewProduct();
		int totalProduct = productService.getTotalProduct();
		totalPages = (totalProduct % 8 == 0) ? (totalProduct / 8) : (totalProduct / 8 + 1);
		listProduct = productService.getListProduct(0);
		if (session.get(Constants.CART_NUMBER) == null) {
			session.put(Constants.CART_NUMBER, 0);
		}
		listCategory = categoryService.getListCategory();
		url = Constants.URL_LIST_PRODUCT;
		return Action.SUCCESS;
	}

	@Override
	public String listProduct() {
		listProduct = productService.getListProduct((currentPage - 1) * 8);
		return Action.SUCCESS;
	}

	@Override
	public String listProductByValue(Map<String, Object> session) {
		if (!WebConstants.DEFAULT.equalsIgnoreCase(sortName) && !WebConstants.ASC.equalsIgnoreCase(sortName)
				&& !WebConstants.DESC.equalsIgnoreCase(sortName)) {
			execute(session);
			return Action.SUCCESS;
		}
		if (!WebConstants.DEFAULT.equalsIgnoreCase(sortPrice) && !WebConstants.ASC.equalsIgnoreCase(sortPrice)
				&& !WebConstants.DESC.equalsIgnoreCase(sortPrice)) {
			execute(session);
			return Action.SUCCESS;
		}
		try {
			double x = Double.parseDouble(priceMin);
			double y = Double.parseDouble(priceMax);
			int z = Integer.parseInt(categoryId);
			if (x < 0 || y < 0 || x > y || z < 0) {
				execute(session);
				return Action.SUCCESS;
			}
		} catch (IllegalArgumentException e) {
			execute(session);
			return Action.SUCCESS;
		}
		listNewProduct = productService.getListNewProduct();
		BigDecimal priceBigMin = new BigDecimal(priceMin);
		BigDecimal priceBigMax = new BigDecimal(priceMax);
		int totalProduct = productService.getTotalProductSelectByValue(Integer.parseInt(categoryId), priceBigMin,
				priceBigMax);
		listCategory = categoryService.getListCategory();
		totalPages = (totalProduct % 8 == 0) ? (totalProduct / 8) : (totalProduct / 8 + 1);
		listProduct = productService.selectListProductByValue(Integer.parseInt(categoryId), sortName, sortPrice,
				priceBigMin, priceBigMax, 0, 8);
		url = Constants.URL_LOAD_LIST_PRODUCT;
		return Action.SUCCESS;
	}

	@Override
	public String loadListProductByValue() {
		// TODO Auto-generated method stub
		BigDecimal priceBigMin = new BigDecimal(priceMin);
		BigDecimal priceBigMax = new BigDecimal(priceMax);
		listProduct = productService.selectListProductByValue(Integer.parseInt(categoryId), sortName, sortPrice,
				priceBigMin, priceBigMax, (currentPage - 1) * 8, 8);
		return Action.SUCCESS;
	}

	public List<Product> getListNewProduct() {
		return listNewProduct;
	}

	public void setListNewProduct(List<Product> listNewProduct) {
		this.listNewProduct = listNewProduct;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortPrice() {
		return sortPrice;
	}

	public void setSortPrice(String sortPrice) {
		this.sortPrice = sortPrice;
	}

	public String getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(String priceMin) {
		this.priceMin = priceMin;
	}

	public String getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(String priceMax) {
		this.priceMax = priceMax;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

}
