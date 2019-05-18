package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fsoft.project.dao.ProductDao;
import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.entity.Product;

public class TestProduct {

	static ProductDao productDao;
	static Product product;
	static List<Product> listProduct;
	static ManuFacturer manufactor;
	static Category category;

	@BeforeClass
	public static void prepareForTest() {
		productDao = new ProductDaoImpl();
		manufactor = new ManuFacturer();
		category = new Category();
	}

	@Test
	public void selectedAllProduct() {
		for (Product list : productDao.allProduct()) {
			assertNotNull(list.getProductName());
		}

	}

	@Test
	public void testCreateProduct() {
		Product p  = new Product();
		Category category = new Category();
		category.setId(1);
		ManuFacturer manuFacturer = new ManuFacturer();
		manuFacturer.setId(1);
		p.setProductName("a");
		p.setCategory(category);
		p.setManuFacturer(manuFacturer);
		p.setPrice(123);
		p.setImagePath("a.jpg");
		p.setColor("red");
		p.setDescription("abc");
		p.setCreateDate(new Date(1231321));
		int expected = 0;
		File file = new File("123");
		int actual =  productDao.addProduct(p, file);
		assertEquals(expected, actual);
	}

	@Test
	public void testFetchProduct() {
		product = productDao.fetchProduct(1);
		assertEquals(1, 1);
	}

	@Test
	public void testDeleteProduct() {
		int id = 1;
		assertEquals(1, productDao.deleteProduct(id));
	}
	
	@Test
	public void testUpdateProduct() {
		Product p  = new Product();
		Category category = new Category();
		category.setId(1);
		ManuFacturer manuFacturer = new ManuFacturer();
		manuFacturer.setId(1);
		p.setProductName("a");
		p.setCategory(category);
		p.setManuFacturer(manuFacturer);
		p.setPrice(123);
		p.setImagePath("a.jpg");
		p.setColor("red");
		p.setDescription("abc");
		p.setCreateDate(new Date(1231321));
		p.setId(4);
		int expected = 1;
		int actual = productDao.updateProduct(p, new File("123"));
	}
	
	@Test
	public void testgetTotalProductSelectByValue() {
		int actual = 0;
		BigDecimal tempBigMin = new BigDecimal(10);
		BigDecimal tempBigMax = new BigDecimal(500);
		actual = productDao.getTotalProductSelectByValue(1, tempBigMin, tempBigMax);
		assertTrue(actual != 0);
	}
	
	@Test
	public void testselectListProductByValue() {
		List<Product> list = null;
		BigDecimal tempBigMin = new BigDecimal(10);
		BigDecimal tempBigMax = new BigDecimal(500);
		list = productDao.selectListProductByValue(1, "ASC", "ASC", tempBigMin, tempBigMax, 0, 1);
		assertTrue(list != null);
	}
	
	@Test
	public void testgetListProductRelated() {
		List<Product> list = null;
		product = new Product();
		category.setId(1);
		manufactor.setId(1);
		product.setId(4);
		product.setColor("GRAY");
		product.setCategory(category);
		product.setManuFacturer(manufactor);
		product.setImagePath("   ");
		product.setDescription("xyz");
		product.setCreateDate(new Date(1825));
		list = productDao.getListProductRelated(product);
		assertTrue(list != null);
	}
	
	@Test
	public void testgetListProductByValue() {
		List<Product> list = null;
		list =	productDao.getListProductByValue("IPHONE");
		assertTrue(list != null);
	}
	
	@Test
	public void testgetProductById() {
		Product p = null;
		p = productDao.getProductById(4);
		assertTrue(p != null);
	}
	
	@Test
	public void testgetListProduct() {
		List<Product> list = null;
		list = productDao.getListProduct(10);
		assertTrue(list != null);
	}
	
	@Test
	public void testgetListNewProduct() {
		List<Product> list = null;
		list = productDao.getListNewProduct();
		assertTrue(list != null);
	}
	
	@Test
	public void testgetTotalProduct() {
		int expected = 0;
		int actual = productDao.getTotalProduct();
		assertTrue(expected != actual);
	}

}
