/**
 * 
 */
package com.fsoft.project.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import com.fsoft.project.entity.Product;

/**
 * @author hungcoutinho
 *
 */
public interface ProductDao {

	List<Product> getListNewProduct();

	List<Product> getListProduct(int n);

	List<Product> getListProductByValue(String val);

	int getTotalProduct();

	Product getProductById(int id);

	List<Product> getListProductRelated(Product product);

	List<Product> selectListProductByValue(int categoryID, String sortName, String sortPrice, BigDecimal priceMin,
			BigDecimal priceMax, int offset, int fetch);

	int getTotalProductSelectByValue(int categoryId, BigDecimal priceMin, BigDecimal priceMax);

	int addProduct(Product product, File myFile);

	List<Product> allProduct();

	Product fetchProduct(int id);

	int updateProduct(Product product , File myFile);

	int deleteProduct(int id);
}
