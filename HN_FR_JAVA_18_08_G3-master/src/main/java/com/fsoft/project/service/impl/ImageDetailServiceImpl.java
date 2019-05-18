/**
 * 
 */
package com.fsoft.project.service.impl;

import java.util.List;

import com.fsoft.project.dao.ImageDetailDao;
import com.fsoft.project.entity.ImageDetail;
import com.fsoft.project.service.ImageDetailService;

/**
 * @author hungcoutinho
 *
 */
public class ImageDetailServiceImpl implements ImageDetailService {

	private ImageDetailDao imageDetailDao;

	public ImageDetailServiceImpl(ImageDetailDao imageDetailDao) {
		this.imageDetailDao = imageDetailDao;
	}

	@Override
	public List<ImageDetail> getListImageDetailByProductId(int productId) {
		// TODO Auto-generated method stub
		return imageDetailDao.getListImageByProductId(productId);
	}

	@Override
	public ImageDetail getImageDetailById(int id) {
		// TODO Auto-generated method stub
		return imageDetailDao.getImageDetailById(id);
	}

}
