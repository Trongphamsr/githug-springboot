/**
 * 
 */
package com.fsoft.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.service.impl.CategoryServiceImpl;

/**
 * @author hungcoutinho
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Mock
	private CategoryDaoImpl categoryDao;

	@Test
	public void testGetCategoryByIdSuccess() {
		Category category = new Category(1, "");
		Mockito.when(categoryDao.getCategoryById(1)).thenReturn(category);
		assertEquals(1, categoryService.getCategoryById(1).getId());
	}

	@Test
	public void testGetCategoryByIdFail() {
		Mockito.when(categoryDao.getCategoryById(1)).thenReturn(null);
		assertEquals(null, categoryService.getCategoryById(1));
	}

	@Test
	public void testGetListCategorySuccess() {
		List<Category> listC = new LinkedList<>();
		listC.add(new Category(1, "Ca"));
		listC.add(new Category(2, "Ca"));
		listC.add(new Category(3, "Ca"));
		Mockito.when(categoryDao.getListCategory()).thenReturn(listC);
		assertEquals(3, categoryService.getListCategory().size());
	}

	@Test
	public void testGetListCategoryFail() {
		List<Category> listC = null;
		Mockito.when(categoryDao.getListCategory()).thenReturn(listC);
		assertEquals(null, categoryService.getListCategory());
	}

	
	@Test
	public void testGetCategoryId() {
		int expected = 0;
		int actual = categoryService.getCategoryId("a");
		assertTrue(expected == actual);
	}


	@Test
	public void testAddCategorySuccess() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.addCategory(category.getName())).thenReturn(1);
		assertTrue(categoryService.addCategory(category.getName()) == 1);
	}

	@Test
	public void testAddCategoryFail() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.addCategory(category.getName())).thenReturn(0);
		assertTrue(categoryService.addCategory(category.getName()) == 0);
	}

	@Test
	public void testAllCategorySuccess() {
		List<Category> listC = new LinkedList<>();
		listC.add(new Category(1, "Ca"));
		listC.add(new Category(2, "Ca"));
		listC.add(new Category(3, "Ca"));
		Mockito.when(categoryDao.allCategory()).thenReturn(listC);
		assertEquals(3, categoryService.allCategory().size());
	}

	@Test
	public void testAllCategoryFail() {
		List<Category> listC = null;
		Mockito.when(categoryDao.allCategory()).thenReturn(listC);
		assertEquals(null, categoryService.allCategory());
	}

	@Test
	public void testUpdateCategorySuccess() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.updateCategory(category.getName(), "Sa")).thenReturn(1);
		assertTrue(categoryService.updateCategory(category.getName(), "Sa") == 1);
	}

	@Test
	public void testUpdateCategoryFail() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.updateCategory(category.getName(), "Sa")).thenReturn(0);
		assertTrue(categoryService.updateCategory(category.getName(), "Sa") == 0);
	}

	@Test
	public void testDeleteCategorySuccess() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.deleteCategory(category.getName())).thenReturn(1);
		assertTrue(categoryService.deleteCategory(category.getName()) == 1);
	}

	@Test
	public void testDeleteCategoryFail() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.deleteCategory(category.getName())).thenReturn(0);
		assertTrue(categoryService.deleteCategory(category.getName()) == 0);
	}

	@Test
	public void testIdCategorySuccess() {
		List<Category> listC = new LinkedList<>();
		listC.add(new Category(1, "Ca"));
		listC.add(new Category(2, "Ca"));
		listC.add(new Category(3, "Ca"));
		Mockito.when(categoryDao.idCategory()).thenReturn(listC);
		assertEquals(3, categoryService.idCategory().size());
	}

	@Test
	public void testIdCategoryFail() {
		List<Category> listC = null;
		Mockito.when(categoryDao.idCategory()).thenReturn(listC);
		assertEquals(null, categoryService.idCategory());
	}

	@Test
	public void testIdManuFacturerSuccess() {
		List<ManuFacturer> listM = new LinkedList<>();
		listM.add(new ManuFacturer(1, "Manu", ""));
		listM.add(new ManuFacturer(2, "Manu", ""));
		listM.add(new ManuFacturer(3, "Manu", ""));
		Mockito.when(categoryDao.idManuFacturer()).thenReturn(listM);
		assertEquals(3, categoryService.idManuFacturer().size());
	}

	@Test
	public void testIdManuFacturerFail() {
		List<ManuFacturer> listM = null;
		Mockito.when(categoryDao.idManuFacturer()).thenReturn(listM);
		assertEquals(null, categoryService.idManuFacturer());
	}

	@Test
	public void testGetCategoryIdByNameSuccess() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.getCategoryId(category.getName())).thenReturn(1);
		assertTrue(categoryService.getCategoryId(category.getName()) == 1);
	}

	@Test
	public void testGetCategoryIdByNameFail() {
		Category category = new Category(1, "Ca");
		Mockito.when(categoryDao.getCategoryId(category.getName())).thenReturn(0);
		assertTrue(categoryService.getCategoryId(category.getName()) == 0);
	}
}
