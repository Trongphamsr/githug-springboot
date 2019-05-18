/**
 * 
 */
package com.fsoft.project.dao;

import java.util.List;

import com.fsoft.project.entity.ImageDetail;

/**
 * @author hungcoutinho
 *
 */
public interface ImageDetailDao {

	List<ImageDetail> getListImageByProductId(int id);

	ImageDetail getImageDetailById(int id);

}
