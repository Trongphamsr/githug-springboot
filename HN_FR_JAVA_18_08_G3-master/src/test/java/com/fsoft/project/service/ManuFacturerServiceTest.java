/**
 * 
 */
package com.fsoft.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fsoft.project.dao.impl.LineItemDaoImpl;
import com.fsoft.project.dao.impl.ManuFacturerDaoImpl;
import com.fsoft.project.entity.LineItem;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.entity.OrderProduct;
import com.fsoft.project.entity.Product;
import com.fsoft.project.service.impl.LineItemServiceImpl;
import com.fsoft.project.service.impl.ManuFacturerServiceImpl;

/**
 * @author hungcoutinho
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ManuFacturerServiceTest {
	
	@InjectMocks
	private ManuFacturerServiceImpl manuFacturerService;
	@Mock
	private ManuFacturerDaoImpl manuFacturerDao;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetManuFacturerByIdSuccess() {
		ManuFacturer manu = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.getManuFacturerById(1)).thenReturn(manu);
		assertTrue("Manu".equals(manuFacturerService.getManuFacturerById(1).getName()));
	}

	@Test
	public void testGetManuFacturerByIdFail() {
		Mockito.when(manuFacturerDao.getManuFacturerById(1)).thenReturn(null);
		assertFalse(manuFacturerService.getManuFacturerById(1) != null);
	}

	@Test
	public void testGetListManuFacturerServiceSuccess() {
		List<ManuFacturer> listM = new LinkedList<>();
		listM.add(new ManuFacturer(1, "Manu", ""));
		listM.add(new ManuFacturer(2, "Manu", ""));
		listM.add(new ManuFacturer(3, "Manu", ""));
		Mockito.when(manuFacturerDao.getListManuFacturer()).thenReturn(listM);
		assertEquals(3, manuFacturerService.getListManuFacturerService().size());
	}

	@Test
	public void testGetListManuFacturerServiceFail() {
		List<ManuFacturer> listM = null;
		Mockito.when(manuFacturerDao.getListManuFacturer()).thenReturn(listM);
		assertEquals(null, manuFacturerService.getListManuFacturerService());
	}

	@Test
	public void testAddManuFacturerSuccess() {
		ManuFacturer manuFacturer = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.addManuFacturer(manuFacturer.getName())).thenReturn(1);
		assertTrue(manuFacturerService.addManuFacturer(manuFacturer.getName()) == 1);
	}

	@Test
	public void testAddManuFacturerFail() {
		ManuFacturer manuFacturer = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.addManuFacturer(manuFacturer.getName())).thenReturn(0);
		assertTrue(manuFacturerService.addManuFacturer(manuFacturer.getName()) == 0);
	}

	@Test
	public void testAllManuFacturerSuccess() {
		List<ManuFacturer> listM = new LinkedList<>();
		listM.add(new ManuFacturer(1, "Manu", ""));
		listM.add(new ManuFacturer(2, "Manu", ""));
		listM.add(new ManuFacturer(3, "Manu", ""));
		Mockito.when(manuFacturerDao.allManuFacturer()).thenReturn(listM);
		assertEquals(3, manuFacturerService.allManuFacturer().size());
	}

	@Test
	public void testAllManuFacturerFail() {
		List<ManuFacturer> listM = null;
		Mockito.when(manuFacturerDao.allManuFacturer()).thenReturn(listM);
		assertEquals(null, manuFacturerService.allManuFacturer());
	}

	@Test
	public void testUpdateManuFacturerSuccess() {
		ManuFacturer manuFacturer = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.updateManuFacturer(manuFacturer.getName(), "")).thenReturn(1);
		assertTrue(manuFacturerService.updateManuFacturer(manuFacturer.getName(), "") == 1);
	}

	@Test
	public void testUpdateManuFacturerFail() {
		ManuFacturer manuFacturer = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.updateManuFacturer(manuFacturer.getName(), "")).thenReturn(0);
		assertTrue(manuFacturerService.updateManuFacturer(manuFacturer.getName(), "") == 0);
	}

	@Test
	public void testDeleteManuFacturerSuccess() {
		ManuFacturer manuFacturer = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.deleteManuFacturer(manuFacturer.getName())).thenReturn(1);
		assertTrue(manuFacturerService.deleteManuFacturer(manuFacturer.getName()) == 1);
	}

	@Test
	public void testDeleteManuFacturerFail() {
		ManuFacturer manuFacturer = new ManuFacturer(1, "Manu", "");
		Mockito.when(manuFacturerDao.deleteManuFacturer(manuFacturer.getName())).thenReturn(0);
		assertTrue(manuFacturerService.deleteManuFacturer(manuFacturer.getName()) == 0);
	}

	@Test
	public void testGetNameManuFacturerId() {
		ManuFacturerService manu = new ManuFacturerServiceImpl(new ManuFacturerDaoImpl());
		int expected = 1;
		int actual = manu.getManufacturerId("ABC");
		assertTrue(expected != actual);
	}
}
