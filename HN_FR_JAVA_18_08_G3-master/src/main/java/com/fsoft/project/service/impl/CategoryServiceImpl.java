/**
 * 
 */
package com.fsoft.project.service.impl;

import java.util.List;

import com.fsoft.project.dao.CategoryDao;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.service.CategoryService;

/**
 * @author hungcoutinho
 *
 */
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;

	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> getListCategory() {
		// TODO Auto-generated method stub
		return categoryDao.getListCategory();
	}

	@Override
	public int addCategory(String name){
		return categoryDao.addCategory(name);
	}

	@Override
	public List<Category> allCategory(){
		return categoryDao.allCategory();
	}

	@Override
	public int updateCategory(String name, String hidden){
		return categoryDao.updateCategory(name, hidden);
	}

	@Override
	public int deleteCategory(String Name){
		return categoryDao.deleteCategory(Name);
	}
	
	@Override
	public List<Category> idCategory(){
		return categoryDao.idCategory();
	}

	@Override
	public List<ManuFacturer> idManuFacturer(){
		return categoryDao.idManuFacturer();
	}

	@Override
	public int getCategoryId(String name) {
		return categoryDao.getCategoryId(name);
	}

}
