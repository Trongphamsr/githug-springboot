/**
 * 
 */
package com.fsoft.project.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.mockito.Mockito;

import com.fsoft.project.dao.impl.ProductDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.impl.ProductServiceImpl;

/**
 * @author hungcoutinho
 *
 */
public class ProductServiceImplTest {

	@Test
	public void testGetListNewProductNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = new LinkedList<>();
		Mockito.when(productDao.getListNewProduct()).thenReturn(listP);
		assertTrue(productService.getListNewProduct() != null);
	}

	@Test
	public void testGetListNewProductNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = null;
		Mockito.when(productDao.getListNewProduct()).thenReturn(listP);
		assertFalse(productService.getListNewProduct() != null);
	}

	@Test
	public void testGetListProductNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = new LinkedList<>();
		Mockito.when(productDao.getListProduct(0)).thenReturn(listP);
		assertTrue(productService.getListProduct(0) != null);
	}

	@Test
	public void testGetListProductNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = null;
		Mockito.when(productDao.getListProduct(0)).thenReturn(listP);
		assertFalse(productService.getListProduct(0) != null);
	}

	@Test
	public void testGetTotalProduct() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Mockito.when(productDao.getTotalProduct()).thenReturn(1);
		assertTrue(productService.getTotalProduct() == 1);
	}

	@Test
	public void testGetProductByIdNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		Mockito.when(productDao.getProductById(1)).thenReturn(product);
		assertTrue(productService.getProductById(1).getId() == 1);
	}

	@Test
	public void testGetProductByIdNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = null;
		Mockito.when(productDao.getProductById(1)).thenReturn(product);
		assertFalse(productService.getProductById(1) != null);
	}

	@Test
	public void testGetProductByValueNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listProduct = new LinkedList<>();
		listProduct.add(new Product(1, "IPhone I", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		listProduct.add(new Product(2, "IPhone II", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		listProduct.add(new Product(3, "IPhone III", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		Mockito.when(productDao.getListProductByValue("I")).thenReturn(listProduct);
		boolean check = true;
		List<Product> listP = productService.getListProductByValue("I");
		Pattern pattern = Pattern.compile("I", Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		for (Product p : listP) {
			matcher = pattern.matcher(p.getProductName());
			if (!matcher.find())
				check = false;
		}
		assertTrue(check);
	}

	@Test
	public void testGetProductByValueNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listProduct = null;
		Mockito.when(productDao.getListProductByValue("I")).thenReturn(listProduct);
		assertFalse(productService.getListProductByValue("I") != null);
	}

	@Test
	public void testGetListProductRelatedNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		List<Product> listProduct = new LinkedList<>();
		listProduct.add(new Product(1, "IPhone I", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		listProduct.add(new Product(2, "IPhone II", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		listProduct.add(new Product(3, "IPhone III", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		listProduct.add(new Product(4, "IPhone IV", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, ""));
		Mockito.when(productDao.getListProductRelated(product)).thenReturn(listProduct);
		assertTrue(productService.getListProductRelated(product) != null);
	}

	@Test
	public void testGetListProductRelatedNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		List<Product> listProduct = null;
		Mockito.when(productDao.getListProductRelated(product)).thenReturn(listProduct);
		assertFalse(productService.getListProductRelated(product) != null);
	}

	@Test
	public void testSelectListProductByValueNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = new LinkedList<>();
		Mockito.when(productDao.selectListProductByValue(1, "DESC", "ASC", new BigDecimal("0"),
				new BigDecimal("30000000"), 0, 8)).thenReturn(listP);
		assertTrue(productService.selectListProductByValue(1, "DESC", "ASC", new BigDecimal("0"),
				new BigDecimal("30000000"), 0, 8) != null);
	}

	@Test
	public void testSelectListProductByValueNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = null;
		Mockito.when(productDao.selectListProductByValue(1, "DESC", "ASC", new BigDecimal("0"),
				new BigDecimal("30000000"), 0, 8)).thenReturn(listP);
		assertFalse(productService.selectListProductByValue(1, "DESC", "ASC", new BigDecimal("0"),
				new BigDecimal("30000000"), 0, 8) != null);
	}

	@Test
	public void testGetTotalProductSelectByValue() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Mockito.when(productDao.getTotalProductSelectByValue(1, new BigDecimal("0"), new BigDecimal("30000000")))
				.thenReturn(1);
		assertTrue(
				productService.getTotalProductSelectByValue(1, new BigDecimal("0"), new BigDecimal("30000000")) == 1);
	}

	@Test
	public void testAddProductSuccess() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		File file = new File("");
		Mockito.when(productDao.addProduct(product, file)).thenReturn(1);
		assertTrue(productService.addProduct(product, file) == 1);
	}

	@Test
	public void testAddProductFail() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		File file = new File("");
		Mockito.when(productDao.addProduct(product, file)).thenReturn(0);
		assertTrue(productService.addProduct(product, file) == 0);
	}

	@Test
	public void testAllProductNotNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = new LinkedList<>();
		Mockito.when(productDao.allProduct()).thenReturn(listP);
		assertTrue(productService.allProduct() != null);
	}

	@Test
	public void testAllProductNull() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		List<Product> listP = null;
		Mockito.when(productDao.allProduct()).thenReturn(listP);
		assertFalse(productService.allProduct() != null);
	}

	@Test
	public void testFetchProductSuccess() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		Mockito.when(productDao.fetchProduct(1)).thenReturn(product);
		assertTrue(productService.fetchProduct(1).getId() == 1);
	}

	@Test
	public void testFetchProductFail() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = null;
		Mockito.when(productDao.fetchProduct(1)).thenReturn(product);
		assertFalse(productService.fetchProduct(1) != null);
	}

	@Test
	public void testUpdateProductSuccess() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		File file = new File("");
		Mockito.when(productDao.updateProduct(product, file)).thenReturn(1);
		assertTrue(productService.updateProduct(product, file) == 1);
	}

	@Test
	public void testUpdateProductFail() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Product product = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"),
				"red", 0, "");
		File file = new File("");
		Mockito.when(productDao.updateProduct(product, file)).thenReturn(0);
		assertTrue(productService.updateProduct(product, file) == 0);
	}

	@Test
	public void testDeleteProductSuccess() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Mockito.when(productDao.deleteProduct(1)).thenReturn(1);
		assertTrue(productService.deleteProduct(1) == 1);
	}

	@Test
	public void testDeleteProductFail() {
		ProductDaoImpl productDao = Mockito.mock(ProductDaoImpl.class);
		ProductServiceImpl productService = new ProductServiceImpl(productDao);
		Mockito.when(productDao.deleteProduct(1)).thenReturn(0);
		assertTrue(productService.deleteProduct(1) == 0);
	}
}
