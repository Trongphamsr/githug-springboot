package com.fsoft.project.service;

import java.sql.SQLException;
import java.util.List;

import com.fsoft.project.entity.LineItem;

public interface LineItemService {
	int addLineItem(List<LineItem> listItem);
}
