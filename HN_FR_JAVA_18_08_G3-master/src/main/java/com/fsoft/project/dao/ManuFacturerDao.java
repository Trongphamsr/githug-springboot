/**
 * 
 */
package com.fsoft.project.dao;

import java.util.List;

import com.fsoft.project.entity.ManuFacturer;

/**
 * @author hungcoutinho
 *
 */
public interface ManuFacturerDao {

	ManuFacturer getManuFacturerById(int id);

	List<ManuFacturer> getListManuFacturer();

	public int addManuFacturer(String name);

	public List<ManuFacturer> allManuFacturer();

	public int updateManuFacturer(String name, String hidden);

	public int deleteManuFacturer(String Name);

	int getManufacturerId(String name);
}
