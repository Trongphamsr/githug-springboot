package com.fsoft.project.action;

import java.util.ArrayList;
import java.util.List;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.CategoryDao;
import com.fsoft.project.dao.impl.CategoryDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.service.CategoryService;
import com.fsoft.project.service.impl.CategoryServiceImpl;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.Preparable;

public class CategoryAction extends BaseAction implements Preparable {

	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private boolean noData = false;
	private String msg;
	
	private CategoryDao categoryDao;
	private CategoryService categoryService;
	List<Category> listCategory = new ArrayList<>();
	List<ManuFacturer> listManuFacturer = new ArrayList<>();
	

	@Override
	public void prepare() throws Exception {
		categoryDao = new CategoryDaoImpl();
		categoryService = new CategoryServiceImpl(categoryDao);
	}
	

	public String idCategory_ManuFacturer() {
		
		try {
			listCategory = categoryService.idCategory();
			listManuFacturer = categoryService.idManuFacturer();

			if (listCategory.size() == 0 && listManuFacturer.size()==0) {
				noData = false;
			} else {
				noData = true;
			}
		} catch (Exception e) {
		}
		return WebConstants.ID_CATEGORY_MANUFACTURER;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public boolean isNoData() {
		return noData;
	}

	public void setNoData(boolean noData) {
		this.noData = noData;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public List<ManuFacturer> getListManuFacturer() {
		return listManuFacturer;
	}

	public void setListManuFacturer(List<ManuFacturer> listManuFacturer) {
		this.listManuFacturer = listManuFacturer;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
