package com.fsoft.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.fsoft.project.dao.OrderProductDao;
import com.fsoft.project.db.DbHelper;
import com.fsoft.project.entity.OrderProduct;
import com.fsoft.project.utils.constants.QueryConstants;

public class OrderProductDaoImpl implements OrderProductDao{
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	@Override
	public int getCurrentIdentity()  {
		int current = 0;
		connection = DbHelper.getConnection();
		if(connection != null) {
			try {
				ps = connection.prepareStatement(QueryConstants.CURRENT_ORDER);
				  rs = ps.executeQuery();
				    while(rs.next()) {
				    	current = rs.getInt(1);
				    }
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DbHelper.closeConnection(connection, ps);
			}
		}
		return current;
	}
	@Override
	public int addOrderProduct(OrderProduct order) {
		int affected = 0;
		connection = DbHelper.getConnection();
		if(connection != null) {
			try {
				ps = connection.prepareStatement(QueryConstants.ADD_ORDER);
				ps.setInt(1, order.getTotalPrice());
				ps.setInt(2, order.getMember().getId());
				ps.setDate(3, order.getCreateDate());
				affected = ps.executeUpdate();
			} catch (SQLException e) {
				affected = 0;
				e.printStackTrace();
			} finally {
				DbHelper.closeConnection(connection, ps);
			}
		}
		return affected;
	}
}
