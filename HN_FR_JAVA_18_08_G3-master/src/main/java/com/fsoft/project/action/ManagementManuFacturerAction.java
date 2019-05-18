package com.fsoft.project.action;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.fsoft.project.dao.ManuFacturerDao;
import com.fsoft.project.dao.impl.ManuFacturerDaoImpl;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.service.ManuFacturerService;
import com.fsoft.project.service.impl.ManuFacturerServiceImpl;
import com.fsoft.project.utils.constants.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ManagementManuFacturerAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String 	Name;
	private String hidden;
	private String msg;
	private boolean noData = false;


	private int result;
	List<ManuFacturer> listManuFacturer = null;
	private ManuFacturerDao manuFacturerDao = new ManuFacturerDaoImpl();
	private ManuFacturerService manuFacturerService= new ManuFacturerServiceImpl(manuFacturerDao);
	public static Logger LOG=Logger.getLogger(ManagementManuFacturerAction.class);


	public String addManuFacturer() throws SQLException, Exception {
		result = manuFacturerService.addManuFacturer(Name);
		if (result > 0) {
			msg = "Registration Successfull";
		} else {
			msg = "Some error";
		}
		return WebConstants.ADD_MANUFACTURER;
	}


	public String allManuFacturer() {
		try {
			listManuFacturer = manuFacturerService.allManuFacturer();
			if (listManuFacturer.size() == 0) {
				noData = false;
			} else {
				noData = true;
			}
		} catch (Exception e) {
			LOG.error("error at all ManuFacturer action",e);
		}
		return WebConstants.ALL_MANUFACTURER;
	}

	public String updateManuFacturer() throws SQLException, Exception {
		result = manuFacturerService.updateManuFacturer(Name, hidden);
		if(result>0) {
			msg="success";
		}
		else {
			msg="false";
		}
		return WebConstants.UPDATE_MANUFACTURER;

	}

	public String deleteManuFacturer() throws SQLException, Exception {
		try {
			result = manuFacturerService.deleteManuFacturer(Name);
			if (result > 0) {
				msg = "Record deleted successfully";
			} else {
				msg = "Some error";
			}
		} catch (SQLException e) {
			LOG.error("error at all ManuFacturer action",e);
		}

		return WebConstants.DELETE_MANUFACTURER;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	


	public List<ManuFacturer> getListManuFacturer() {
		return listManuFacturer;
	}



	public void setListManuFacturer(List<ManuFacturer> listManuFacturer) {
		this.listManuFacturer = listManuFacturer;
	}



	public boolean isNoData() {
		return noData;
	}



	public void setNoData(boolean noData) {
		this.noData = noData;
	}


}
