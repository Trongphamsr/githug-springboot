package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.Product;
import com.trungtamjava.model.ProductDTO;

public interface ProductDao {

	public void addProduct(Product product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(int id);
	
	public Product getProductById(int id);
	
	public List<Product> getAllProducts();
	
}
