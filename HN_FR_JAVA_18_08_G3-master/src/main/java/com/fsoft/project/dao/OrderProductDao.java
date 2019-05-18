package com.fsoft.project.dao;

import java.sql.SQLException;

import com.fsoft.project.entity.OrderProduct;

public interface OrderProductDao {
	int getCurrentIdentity() throws SQLException;
	
	int addOrderProduct(OrderProduct order);
}
