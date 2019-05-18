package com.fsoft.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.fsoft.project.entity.LineItem;

public interface LineItemDao {
	int addLineItem(List<LineItem> listItem);
}
