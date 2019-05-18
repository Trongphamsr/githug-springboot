package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.service.CategoryService;
import com.fsoft.project.service.impl.CategoryServiceImpl;

public class TestCategoryService {

	private CategoryService categoryService;

	@Before
	public void setUp() throws Exception {
		categoryService = new CategoryServiceImpl(new CategoryDaoImpl());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCategory() {
		int expected = 1;
		int actual = categoryService.addCategory("ABC");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAllCategory() {
		List<Category> list = null;
		list = categoryService.allCategory();
		assertTrue(list != null);
	}
	
	@Test
	public void testUpdateCategory() {
		int expected = 1;
		int actual = categoryService.updateCategory("ABC", "DEF");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteCategory() {
		int actual = categoryService.deleteCategory("ABC");
		assertTrue(actual != 0);
	}
	
	@Test
	public void testIdCategory() {
		List<Category> list = null;
		list = categoryService.idCategory();
		assertTrue(list != null);
	}
	
	@Test
	public void testIdManuFacturer() {
		List<ManuFacturer> list = null;
		list = categoryService.idManuFacturer();
		assertTrue(list != null);
	}

}
