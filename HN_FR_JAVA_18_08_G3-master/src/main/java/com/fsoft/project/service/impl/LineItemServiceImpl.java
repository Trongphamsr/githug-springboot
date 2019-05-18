package com.fsoft.project.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.fsoft.project.dao.LineItemDao;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.service.LineItemService;

public class LineItemServiceImpl implements LineItemService{
	private LineItemDao lineItemDao;
	
	public LineItemServiceImpl(LineItemDao dao) {
		this.lineItemDao = dao;
	}
	@Override
	public int addLineItem(List<LineItem> listItem){
		return lineItemDao.addLineItem(listItem);
	}

}
