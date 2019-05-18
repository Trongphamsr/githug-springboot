/**
 * 
 */
package com.fsoft.project.service;

import java.util.Map;

/**
 * @author Nguyen Son
 *
 */
public interface HomeService {

	String execute(Map<String, Object> session);

	String listProduct();

	String listProductByValue(Map<String, Object> session);

	String loadListProductByValue();
}
