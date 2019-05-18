/**
 * 
 */
package com.fsoft.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fsoft.project.dao.ManuFacturerDao;
import com.fsoft.project.db.DbHelper;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.utils.LogUtils;
import com.fsoft.project.utils.constants.QueryConstants;

/**
 * @author hungcoutinho
 *
 */
public class ManuFacturerDaoImpl implements ManuFacturerDao {

	private Connection conn;
	private PreparedStatement pre;
	private ResultSet rs;

	@Override
	public ManuFacturer getManuFacturerById(int id) {
		// TODO Auto-generated method stub
		ManuFacturer manuFacturer = null;
		conn = DbHelper.getConnection();
		if (conn != null) {
			try {
				pre = conn.prepareStatement(QueryConstants.SELECT_MANUFACTURER_BY_ID);
				pre.setInt(1, id);
				ResultSet rs = pre.executeQuery();
				if (rs.next()) {
					manuFacturer = new ManuFacturer(rs.getInt("Id"), rs.getString("Name"), rs.getString("Description"));
				}
			} catch (SQLException e) {
				LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e.getMessage());
			} finally {
				DbHelper.closeConnection(conn, pre, rs);
			}
		}
		return manuFacturer;
	}

	@Override
	public List<ManuFacturer> getListManuFacturer() {
		// TODO Auto-generated method stub
		List<ManuFacturer> listM = null;
		conn = DbHelper.getConnection();
		if (conn != null) {
			listM = new LinkedList<>();
			try {
				pre = conn.prepareStatement(QueryConstants.SELECT_MANUFACTURER);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					listM.add(new ManuFacturer(rs.getInt("Id"), rs.getString("Name"), rs.getString("Description")));
				}
			} catch (SQLException e) {
				LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e.getMessage());
			} finally {
				DbHelper.closeConnection(conn, pre, rs);
			}
		}
		return listM;
	}

	public int addManuFacturer(String name) {
		int result = 0;
		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.ADD_MANUFACTURER);
				pre.setString(1, name);
				result = pre.executeUpdate();
			}
		} catch (SQLException e) {
			LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre);
		}
		return result;
	}

	@Override
	public List<ManuFacturer> allManuFacturer() {
		List<ManuFacturer> listManuFacturer = new ArrayList<ManuFacturer>();
		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.ALL_MANUFACTURER);
				rs = pre.executeQuery();
				int i = 0;
				if (rs != null) {
					while (rs.next()) {
						i++;
						ManuFacturer manuFacturer = new ManuFacturer();
						manuFacturer.setId(i);
						manuFacturer.setName(rs.getString("Name"));
						listManuFacturer.add(manuFacturer);
					}
				}
			}

		} catch (SQLException e) {
			LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre, rs);
		}
		return listManuFacturer;
	}

	@Override

	public int updateManuFacturer(String name, String hidden) {
		int result = 0;
		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.UPDATE_MANUFACTURER);
				pre.setString(1, name);
				pre.setString(2, hidden);
				result = pre.executeUpdate();
			}
		} catch (SQLException e) {
			LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre, rs);
		}
		return result;
	}

	@Override
	public int deleteManuFacturer(String Name) {

		int result = 0;
		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.DELETE_MANUFACTURER);
				pre.setString(1, Name);
				result = pre.executeUpdate();
			}

		} catch (SQLException e) {
			LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e);
		} finally {
			DbHelper.closeConnection(conn, pre);
		}
		return result;
	}

	@Override
	public int getManufacturerId(String name) {
		int result = 0;
		conn = DbHelper.getConnection();
		if (conn != null) {
			try {
				pre = conn.prepareStatement(QueryConstants.GET_ID_MANUFACTURER);
				pre.setString(1, name);
				rs = pre.executeQuery();
				if (rs.next()) {
					result = rs.getInt("Id");
				}
			} catch (SQLException e) {
				LogUtils.getLogger(ManuFacturerDaoImpl.class.getName()).error(e.getMessage());
			} finally {
				DbHelper.closeConnection(conn, pre);
			}
		}
		return result;
	}
}
