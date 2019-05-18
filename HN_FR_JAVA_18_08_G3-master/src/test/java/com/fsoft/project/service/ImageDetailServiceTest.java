/**
 * 
 */
package com.fsoft.project.service;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fsoft.project.dao.impl.ImageDetailDaoImpl;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ImageDetail;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.impl.ImageDetailServiceImpl;

/**
 * @author hungcoutinho
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ImageDetailServiceTest {
	@InjectMocks
	private ImageDetailServiceImpl imageDetailService;
	@Mock
	private ImageDetailDaoImpl imageDetailDao;

	@Test
	public void testGetListImageDetailByProductIdSuccess() {
		Product p = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"), "red",
				0, "");
		List<ImageDetail> listI = new LinkedList<>();
		listI.add(new ImageDetail(1, "1", p));
		listI.add(new ImageDetail(2, "1", p));
		listI.add(new ImageDetail(3, "1", p));
		Mockito.when(imageDetailDao.getListImageByProductId(1)).thenReturn(listI);
		assertEquals(3, imageDetailService.getListImageDetailByProductId(p.getId()).size());
	}

	@Test
	public void testGetListImageDetailByProductIdFail() {
		Product p = new Product(1, "IPhone", "", new ManuFacturer(), new Category(), Date.valueOf("2018-09-09"), "red",
				0, "");
		List<ImageDetail> listI = null;
		Mockito.when(imageDetailDao.getListImageByProductId(1)).thenReturn(listI);
		assertEquals(null, imageDetailService.getListImageDetailByProductId(p.getId()));
	}

	@Test
	public void testGetImageDetailByIdSuccess() {
		ImageDetail imageDetail = new ImageDetail(1, "Image", new Product());
		Mockito.when(imageDetailDao.getImageDetailById(1)).thenReturn(imageDetail);
		assertEquals("Image", imageDetailService.getImageDetailById(1).getImagePath());
	}

	@Test
	public void testGetImageDetailByIdFail() {
		ImageDetail imageDetail = null;
		Mockito.when(imageDetailDao.getImageDetailById(1)).thenReturn(imageDetail);
		assertEquals(null, imageDetailService.getImageDetailById(1));
	}
}
