/**
 * 
 */
package com.fsoft.project.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.fsoft.project.dao.ProductDao;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.ProductService;

/**
 * @author hungcoutinho
 *
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getListNewProduct() {
		return productDao.getListNewProduct();
	}

	@Override
	public List<Product> getListProduct(int n) {
		return productDao.getListProduct(n);
	}

	@Override
	public int getTotalProduct() {
		return productDao.getTotalProduct();
	}

	@Override
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getListProductByValue(String val) {
		return productDao.getListProductByValue(val);
	}

	@Override
	public List<Product> getListProductRelated(Product product) {
		return productDao.getListProductRelated(product);
	}

	@Override
	public List<Product> selectListProductByValue(int categoryId, String sortName, String sortPrice,
			BigDecimal priceMin, BigDecimal priceMax, int offset, int fetch) {
		return productDao.selectListProductByValue(categoryId, sortName, sortPrice, priceMin, priceMax, offset, fetch);
	}

	@Override
	public int getTotalProductSelectByValue(int categoryId, BigDecimal priceMin, BigDecimal priceMax){
		return productDao.getTotalProductSelectByValue(categoryId, priceMin, priceMax);
	}

	@Override
	public int addProduct(Product product, File myFile) {
		return productDao.addProduct(product, myFile);
	}

	@Override
	public List<Product> allProduct() {
		return productDao.allProduct();
	}

	@Override
	public Product fetchProduct(int id) {
		return productDao.fetchProduct(id);
	}

	@Override
	public int updateProduct(Product product, File myFile) {
		return productDao.updateProduct(product, myFile);
	}

	@Override
	public int deleteProduct(int id) {
		return productDao.deleteProduct(id);
	}
}
