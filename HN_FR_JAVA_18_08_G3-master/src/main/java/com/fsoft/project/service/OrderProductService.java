package com.fsoft.project.service;

import java.sql.SQLException;
import java.util.Map;

public interface OrderProductService {
	int getCurrentIdentity() throws SQLException;
	
	int addOrderProduct(Map<String, Object> session, int totalPrice);
	
	void removeSession(Map<String, Object> session);
}
