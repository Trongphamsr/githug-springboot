package com.fsoft.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsoft.project.dao.ManuFacturerDao;
import com.fsoft.project.dao.impl.ManuFacturerDaoImpl;
import com.fsoft.project.entity.ManuFacturer;

public class TestManufacturerDao {

	private ManuFacturerDao manuFacturerDao;
	@Before
	public void setUp() throws Exception {
		manuFacturerDao = new ManuFacturerDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetManuFacturerById() {
		ManuFacturer manu = null;
		manu = manuFacturerDao.getManuFacturerById(1);
		assertTrue(manu != null);
	}

	@Test
	public void testDetListManuFacturer() {
		List<ManuFacturer> list = null;
		list = manuFacturerDao.getListManuFacturer();
		assertTrue(list != null);
	}
	
	@Test
	public void testAddManuFacturer() {
		int expected = 1;
		int actual = manuFacturerDao.addManuFacturer("ABC");
		assertTrue(expected != actual);
	}
	
	@Test
	public void testAllManuFacturer() {
		List<ManuFacturer> list = null;
		list = manuFacturerDao.allManuFacturer();
		assertTrue(list != null);
	}
	
	@Test
	public void testUpdateManuFacturer() {
		int actual = manuFacturerDao.updateManuFacturer("ABC", "CBD");
		assertTrue(actual != 0);
	}
	
	@Test
	public void testDeleteManuFacturer() {
		int actual = manuFacturerDao.deleteManuFacturer("ABC");
		assertTrue(actual != 0);
	}
	
	@Test
	public void testGetManufacturerId() {
		int actual = manuFacturerDao.getManufacturerId("ABC");
		assertTrue(actual != 0);
	}
}
