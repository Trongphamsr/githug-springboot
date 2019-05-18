/**
 * 
 */
package com.fsoft.project.dao;

import java.util.List;

import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;

/**
 * @author hungcoutinho
 *
 */
public interface CategoryDao {

	Category getCategoryById(int id);

	List<Category> getListCategory();

	int addCategory(String name);

	List<Category> allCategory();

	int updateCategory(String name, String hidden);

	int deleteCategory(String Name);

	List<Category> idCategory(); // 

	List<ManuFacturer> idManuFacturer();
	
	int getCategoryId(String name);
	
	

}
