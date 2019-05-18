package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fsoft.project.dao.CategoryDao;
import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;

public class TestCategory {
	static CategoryDao categoryDao;
	static Category category;
	static List<Category> listCategory;

	@BeforeClass
	public static void prepareForTest() {
		categoryDao = new CategoryDaoImpl();
	}

	@Test
	public void selectedAllCategory() {
		for (Category list : categoryDao.allCategory()) {
			assertNotNull(list.getName());
		}
	}

	@Test
	public void testCreateCategory() {
		category = new Category();
		category.setName("IPHONE");
		assertEquals(1, categoryDao.addCategory("IPHONE"));
	}

	@Test
	public void testDeleteCategory() {
		String Name = "IPHONE";
		assertEquals(1, categoryDao.deleteCategory(Name));
	}

	@Test
	public void testEditCategory() {
		category = new Category();
		category.setName("SAMSUNG");

		String Name = "SAMSUNG";
		String hidden = "SAMSUNG";

		int actual = categoryDao.updateCategory(Name, hidden);

		int expected = 1;
		assertEquals(expected, actual);

	}

	@Test
	public void testgetCategoryById() {
		Category category = null;
		category = categoryDao.getCategoryById(1);
		assertTrue(category != null);
	}
	
	@Test
	public void testgetListCategory() {
		List<Category> list = null;
		list = categoryDao.getListCategory();
		assertTrue(list != null);
	}
	
	@Test
	public void testIdCategory() {
		List<Category> list = null;
		list = categoryDao.idCategory();
		assertTrue(list != null);
	}
	
	@Test
	public void testIdManuFacturer() {
		List<ManuFacturer> list = null;
		list = categoryDao.idManuFacturer();
		assertTrue(list != null);
	}
	
	@Test
	public void testGetCategoryId() {
		int expected = 0;
		int actual = categoryDao.getCategoryId("IPHONE");
		assertTrue(expected != actual);
	}
}
