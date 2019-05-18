package com.fsoft.project.service.impl;

import java.util.Map;

import com.fsoft.project.service.LoginService;
import com.fsoft.project.utils.constants.Constants;
import com.fsoft.project.utils.constants.WebConstants;

public class LoginServiceImpl implements LoginService {
	
	private int productId;
	public LoginServiceImpl() {

	}


	/*@Override
	public Member getMember(String email, String password) {
		return loginDao.getMember(email, password);
	}*/

	@Override
	public String loginPage(Map<String, Object> session, int index) {
		if (index == Constants.INDEX) {
			return WebConstants.MEMBER_INDEX;
		} else if (index == Constants.PRODUCT_DETAIL) {
			productId = (int) session.put(Constants.PRODUCT, productId);
			return WebConstants.MEMBER_PRODUCT_DETAIL;
		} else if (index == Constants.SHOPPING_CART) {
			return WebConstants.MEMBER_SHOPPING_CART;
		} else if (index == Constants.CHECKOUT) {
			return WebConstants.MEMBER_CHECKOUT;
		} else {
			return WebConstants.SUCCESS_ADMIN;
		}
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
