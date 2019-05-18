/*
 * @(#)BeanUtil.java
 *
 * Copyright 2017 by FPT., All rights reserved.
 *
 * This software is the confidential and proprietary information of FPT.
 * ("Confidential Information").
 */
package vn.com.fpt.clt.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

@SuppressWarnings("deprecation")
public class BeanUtil {

	/**
	 * Copy properties
	 * 
	 * @param source
	 * @param clazz
	 * @return
	 */
	public static <U, V> V copy(U source, V target) {
		BeanUtils.copyProperties(source, target);
		return target;
	}

	/**
	 * Create and copy properties
	 * 
	 * @param source
	 * @param clazz
	 * @return
	 */
	
	public static <U, V> V createAndCopy(U source, Class<V> clazz) {
		return copy(source, BeanUtils.instantiate(clazz));
	}

	/**
	 * Copy List object
	 * 
	 * @param source
	 * @param clazz
	 * @return
	 */
	public static <U, V> List<V> copyList(List<U> sources, Class<V> clazz) {
		List<V> targetList = new ArrayList<V>();
		sources.forEach(source -> {
			targetList.add(createAndCopy(source, clazz));
		});

		return targetList;
	}

}
