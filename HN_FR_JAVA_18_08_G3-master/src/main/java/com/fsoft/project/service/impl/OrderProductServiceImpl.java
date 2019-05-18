package com.fsoft.project.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fsoft.project.dao.OrderProductDao;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.Member;
import com.fsoft.project.entity.OrderProduct;
import com.fsoft.project.service.OrderProductService;
import com.fsoft.project.utils.DateUtils;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;

public class OrderProductServiceImpl implements OrderProductService {
	private OrderProductDao orderProductDao;
	private List<LineItem> listItem;

	public OrderProductServiceImpl(OrderProductDao dao) {
		orderProductDao = dao;
	}

	@Override
	public int getCurrentIdentity() throws SQLException {
		return orderProductDao.getCurrentIdentity();
	}

	@Override
	public int addOrderProduct(Map<String, Object> session, int totalPrice) {
		OrderProduct order = new OrderProduct();
		Member member = (Member) session.get(Constants.MEMBER);
		order.setTotalPrice(totalPrice);
		order.setMember(member);
		order.setCreateDate(DateUtils.getDate());
		return orderProductDao.addOrderProduct(order);
	}

	@Override
	public void removeSession(Map<String, Object> session) {
		session.remove(Constants.LIST_LINE_ITEM);
		session.remove(WebConstants.LIST_PRODUCT);
		session.remove(WebConstants.LIST_LINEITEM);
		session.remove(Constants.CART_NUMBER);
	}
}
