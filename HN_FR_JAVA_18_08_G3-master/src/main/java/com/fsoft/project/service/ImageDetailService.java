/**
 * 
 */
package com.fsoft.project.service;

import java.util.List;

import com.fsoft.project.entity.ImageDetail;

/**
 * @author hungcoutinho
 *
 */
public interface ImageDetailService {

	List<ImageDetail> getListImageDetailByProductId(int productId);

	ImageDetail getImageDetailById(int id);
}
