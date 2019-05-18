/**
 * 
 */
package com.fsoft.project.entity;

import java.io.Serializable;

/**
 * @author hungcoutinho
 *
 */
public class ImageDetail implements Serializable{

	private int id;
	private String imagePath;
	private Product product;

	public ImageDetail() {
		super();
	}

	public ImageDetail(int id, String imagePath, Product product) {
		super();
		this.id = id;
		this.imagePath = imagePath;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
