package com.fsoft.project.action;

import java.util.List;

import com.fsoft.project.base.BaseAction;
import com.fsoft.project.dao.impl.LineItemDaoImpl;
import com.fsoft.project.dao.impl.OrderProductDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.service.LineItemService;
import com.fsoft.project.service.OrderProductService;
import com.fsoft.project.service.impl.LineItemServiceImpl;
import com.fsoft.project.service.impl.OrderProductServiceImpl;
import com.fsoft.project.utils.constants.Constants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

public class CheckoutAction extends BaseAction implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6639946862164365278L;

	private List<LineItem> listItem;
	private boolean message;
	private OrderProductService orderService;
	private LineItemService lineService;
	
	@Override
	public void prepare() throws Exception {
		orderService = new OrderProductServiceImpl(new OrderProductDaoImpl());
		lineService = new LineItemServiceImpl(new LineItemDaoImpl());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Object object = getSession().get(Constants.LIST_LINE_ITEM);
		if (object != null) {
			listItem = (List<LineItem>) object;
		}
		getSession().put(Constants.PAGE_INDEX, Constants.CHECKOUT);
		return Action.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String confirmPayment() {
		int affectedOrder = 0;
		int affectedLineItem = 0;
		listItem = (List<LineItem>) getSession().get(Constants.LIST_LINE_ITEM);
		affectedOrder = orderService.addOrderProduct(getSession(), totalPrice(listItem));
		affectedLineItem = lineService.addLineItem(listItem);
		if(affectedOrder != 0 && affectedLineItem != 0) {
			 message = true;
			 orderService.removeSession(getSession());
		}else 
			message = false;
		return Action.SUCCESS;
	}
	
	private int totalPrice(List<LineItem> list) {
		int sum = 0;
		for(LineItem i : list) {
			sum += i.getNumberProduct() * i.getProduct().getPrice();
		}
		return sum;
	}
	

	public boolean isMessage() {
		return message;
	}

	public void setMessage(boolean message) {
		this.message = message;
	}

	public List<LineItem> getListItem() {
		return listItem;
	}

	public void setListItem(List<LineItem> listItem) {
		this.listItem = listItem;
	}
}
