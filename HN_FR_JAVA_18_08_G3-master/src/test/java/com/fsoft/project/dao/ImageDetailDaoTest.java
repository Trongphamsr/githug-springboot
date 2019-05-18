/**
 * 
 */
package com.fsoft.project.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.fsoft.project.dao.impl.ImageDetailDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ImageDetail;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.entity.Product;

/**
 * @author hungcoutinho
 *
 */
public class ImageDetailDaoTest {

	@Test
	public void testGetListImageByProductId() throws SQLException {
		Product product = new Product(1, "Iphone", "", new ManuFacturer(1, "Manu", ""), new Category(1, "Ca"),
				Date.valueOf("2018-09-09"), "red", 0, "");
		ImageDetailDaoImpl imageDetailDao = new ImageDetailDaoImpl();
		List<ImageDetail> listI = imageDetailDao.getListImageByProductId(product.getId());
		assertTrue(listI != null);
	}

	@Test
	public void testGetImageDetailById() {
		ImageDetailDaoImpl imageDetailDao = new ImageDetailDaoImpl();
		ImageDetail imageDetail = imageDetailDao.getImageDetailById(1);
		assertTrue(imageDetail != null);
	}
}
