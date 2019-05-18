/**
 * 
 */
package com.fsoft.project.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.fsoft.project.entity.Product;

/**
 * @author hungcoutinho
 *
 */
public interface ProductService {

	List<Product> getListNewProduct();

	List<Product> getListProduct(int n);

	Product getProductById(int id);

	int getTotalProduct();

	List<Product> getListProductByValue(String val);

	List<Product> getListProductRelated(Product product);

	List<Product> selectListProductByValue(int categoryId, String sortName, String sortPrice, BigDecimal priceMin,
			BigDecimal priceMax, int offset, int fetch);

	int getTotalProductSelectByValue(int categoryId, BigDecimal priceMin, BigDecimal priceMax);

	int addProduct(Product product, File myFile);

	List<Product> allProduct();

	Product fetchProduct(int id);

	int updateProduct(Product product , File myFile);

	int deleteProduct(int id);
}
