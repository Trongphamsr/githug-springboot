package com.trungtamjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.ProductDao;
import com.trungtamjava.entity.Product;
import com.trungtamjava.entity.User;
import com.trungtamjava.model.ProductDTO;
import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.ProductService;

@Service
@Transactional

public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	public void addProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setImageURL(productDTO.getImageURL());
		productDao.addProduct(product);
	}

	public void updateProduct(ProductDTO productDTO) {
		Product product= productDao.getProductById(productDTO.getId());
		if(product != null){
			product.setName(productDTO.getName());
			product.setPrice(productDTO.getPrice());
		
		productDao.updateProduct(product);	
		}
	}

	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		//userDao.deleteUser(id);
		
		Product product = productDao.getProductById(id);
			if(product !=null){
				productDao.deleteProduct(id);
			}
	}

	public ProductDTO getProductById(int id) {
		// TODO Auto-generated method stub
		//return userDao.getUserById(id);
		
		Product product = productDao.getProductById(id);
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setImageURL(product.getImageURL());
		return productDTO;
		
		
	}

	public List<ProductDTO> getAllProducts() {
		
		List<Product> products = productDao.getAllProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for(Product product: products){
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTOs.add(productDTO);
			
		}
		return productDTOs;
		// TODO Auto-generated method stub
		//return userDao.getAllUsers();
	}
}
