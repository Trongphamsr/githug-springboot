/**
 * 
 */
package com.fsoft.project.entity;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author hungcoutinho
 *
 */
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3498331984594123865L;
	private int id;
	private String productName;
	private String imagePath;
	private ManuFacturer manuFacturer;
	private Category category;
	private Date createDate;
	private String color;
	private int price;
	private String description;
	

	public Product() {  
		super();
	}

	public Product(int id, String productName, String imagePath, ManuFacturer manuFacturer, Category category,
			Date createDate, String color, int price, String description) {
		super();
		this.id = id;
		this.productName = productName;
		this.imagePath = imagePath;
		this.manuFacturer = manuFacturer;
		this.category = category;
		this.createDate = createDate;
		this.color = color;
		this.price = price;
		this.description = description; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public ManuFacturer getManuFacturer() {
		return manuFacturer;
	}

	public void setManuFacturer(ManuFacturer manuFacturer) {
		this.manuFacturer = manuFacturer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
