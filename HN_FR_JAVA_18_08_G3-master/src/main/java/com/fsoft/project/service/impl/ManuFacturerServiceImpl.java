/**
 * 
 */
package com.fsoft.project.service.impl;

import java.util.List;

import com.fsoft.project.dao.ManuFacturerDao;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.service.ManuFacturerService;

/**
 * @author hungcoutinho
 *
 */
public class ManuFacturerServiceImpl implements ManuFacturerService {

	private ManuFacturerDao manuFacturerDao;

	public ManuFacturerServiceImpl(ManuFacturerDao manuFacturerDao) {
		this.manuFacturerDao = manuFacturerDao;
	}

	@Override
	public ManuFacturer getManuFacturerById(int id) {
		// TODO Auto-generated method stub
		return manuFacturerDao.getManuFacturerById(id);
	}

	@Override
	public List<ManuFacturer> getListManuFacturerService() {
		// TODO Auto-generated method stub
		return manuFacturerDao.getListManuFacturer();
	}

	@Override
	public int addManuFacturer(String name) {
		return manuFacturerDao.addManuFacturer(name);
	}

	@Override
	public List<ManuFacturer> allManuFacturer() {
		return manuFacturerDao.allManuFacturer();
	}

	@Override
	public int updateManuFacturer(String name, String hidden) {
		return manuFacturerDao.updateManuFacturer(name, hidden);
	}

	@Override
	public int deleteManuFacturer(String Name) {
		return manuFacturerDao.deleteManuFacturer(Name);
	}

	@Override
	public int getManufacturerId(String name) {
		return manuFacturerDao.getManufacturerId(name);
	}

}
