/**
 * 
 */
package com.fsoft.project.service;

import java.util.List;

import com.fsoft.project.entity.ManuFacturer;

/**
 * @author hungcoutinho
 *
 */
public interface ManuFacturerService {

	ManuFacturer getManuFacturerById(int id);

	List<ManuFacturer> getListManuFacturerService();

	public int addManuFacturer(String name) throws Exception;

	public List<ManuFacturer> allManuFacturer() throws Exception;

	public int updateManuFacturer(String name, String hidden) throws Exception;

	public int deleteManuFacturer(String Name) throws Exception;

	int getManufacturerId(String name);
}
