package com.trungtamjava.service;

import java.util.List;

import com.trungtamjava.model.ProductDTO;

public interface ProductService {

	public void addProduct(ProductDTO product);
	
	public void updateProduct(ProductDTO product);
	
	public void deleteProduct(int id);
	
	public ProductDTO getProductById(int id);
	
	public List<ProductDTO> getAllProducts();
	
	
}
