package com.fsoft.project.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.fsoft.project.dao.CategoryDao;
import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.service.CategoryService;
import com.fsoft.project.service.impl.CategoryServiceImpl;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ManagementCategoryAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String 	Name;
	private String hidden;
	private String msg;
	private boolean noData = false;
	private int result;
	List<Category> listCategory = null;
	private CategoryDao categoryDao = new CategoryDaoImpl();
	private CategoryService categoryService= new CategoryServiceImpl(categoryDao);
	public static Logger LOG=Logger.getLogger(ManagementCategoryAction.class);


	public String addCategory() throws SQLException, Exception {
		result=categoryService.addCategory(Name);
		if (result > 0) {
			msg = "Registration Successfull";
		} else {
			msg = "Some error";
		}
		return WebConstants.ADD_CATEGORY;
	}



	public String allCategory() {

		try {
			listCategory = categoryService.allCategory();

			if (listCategory.size() == 0) {
				noData = false;
			} else {
				noData = true;
			}
		} catch (Exception e) {
			LOG.error("error at all category action",e);
		}
		return WebConstants.ALL_CATEGORY;
	}

	
	public String updateCategory() throws SQLException, Exception {
		result = categoryService.updateCategory(Name, hidden);
		if (result > 0) {	
			msg = "Successfull";
		} else {
			msg = "error please check again";
		}
		return WebConstants.UPDATE_CATEGORY;

	}

	public String deleteCategory() throws SQLException, Exception {
		result = categoryService.deleteCategory(Name);
		if (result > 0) {
			msg = "Record deleted successfully";
		} else {
			msg = "Some error";
		}
		return WebConstants.DELETE_CATEGORY;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public List<Category> getListCategory() {
		return listCategory;
	}



	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}



	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}


}
