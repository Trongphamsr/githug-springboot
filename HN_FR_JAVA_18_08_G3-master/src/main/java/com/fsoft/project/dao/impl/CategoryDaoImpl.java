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

import com.fsoft.project.dao.CategoryDao;
import com.fsoft.project.db.DbHelper;
import com.fsoft.project.entity.Category;
import com.fsoft.project.entity.ManuFacturer;
import com.fsoft.project.utils.LogUtils;
import com.fsoft.project.utils.constants.QueryConstants;

public class CategoryDaoImpl implements CategoryDao {

	private Connection conn;
	private PreparedStatement pre;
	private ResultSet rs;

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		Category category = null;
		conn = DbHelper.getConnection();
		if (conn != null) {
			try {
				pre = conn.prepareStatement(QueryConstants.SELECT_CATEGORY_BY_ID);
				pre.setInt(1, id);
				rs = pre.executeQuery();
				if (rs.next()) {
					category = new Category(rs.getInt("Id"), rs.getString("Name"));
				}
			} catch (SQLException e) {
				LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
			} finally {
				DbHelper.closeConnection(conn, pre, rs);
			}
		}
		return category;
	}

	@Override
	public List<Category> getListCategory() {
		List<Category> listC = null;
		conn = DbHelper.getConnection();
		if (conn != null) {
			listC = new LinkedList<>();
			try {
				pre = conn.prepareStatement(QueryConstants.SELECT_CATEGORY);
				ResultSet rs = pre.executeQuery();
				while (rs.next()) {
					listC.add(new Category(rs.getInt("Id"), rs.getString("Name")));
				}

			} catch (SQLException e) {
				LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
			} finally {
				DbHelper.closeConnection(conn, pre);
			}
		}
		return listC;
	}

	@Override
	public int addCategory(String name) {
		int result = 0;
		try {
			conn = DbHelper.getConnection();

			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.ADD_CATEGORY);
				pre.setString(1, name);
				result = pre.executeUpdate();
			}
		} catch (SQLException e) {
			LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre);
		}
		return result;
	}

	@Override
	public List<Category> allCategory() {

		List<Category> listCategory = new ArrayList<Category>();

		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.ALL_CATEGORY);
				rs = pre.executeQuery();
				int i = 0;

				if (rs != null) {
					while (rs.next()) {
						i++;
						Category category = new Category();
						category.setId(i);
						category.setName(rs.getString("Name"));
						listCategory.add(category);
					}
				}
			}
			conn.commit();
		} catch (SQLException e) {
			LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre, rs);
		}
		return listCategory;
	}

	@Override
	public int updateCategory(String name, String hidden) {
		int result = 0;
		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.UPDATE_CATEGORY);
				pre.setString(1, name);
				pre.setString(2, hidden);
				result = pre.executeUpdate();
			}

		} catch (SQLException e) {
			LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre);
		}
		return result;
	}

	@Override
	public int deleteCategory(String Name) {

		int result = 0;
		try {
			conn = DbHelper.getConnection();
			DbHelper.getConnection().setAutoCommit(false);
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.DELETE_CATEGORY);
				pre.setString(1, Name);
				result = pre.executeUpdate();
			}
		} catch (SQLException e) {
			LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre);
		}
		return result;
	}

	@Override
	public List<Category> idCategory() {
		List<Category> listCategory = new ArrayList<Category>();
		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.ID_CATEGORY);
				rs = pre.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						Category category = new Category();
						category.setId(rs.getInt("Id"));
						category.setName(rs.getString("Name"));
						listCategory.add(category);
					}
				}
			}
		} catch (SQLException e) {
			LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre, rs);
		}
		return listCategory;
	}

	@Override
	public List<ManuFacturer> idManuFacturer() {
		List<ManuFacturer> listManuFacturer = new ArrayList<ManuFacturer>();

		try {
			conn = DbHelper.getConnection();
			if (conn != null) {
				pre = conn.prepareStatement(QueryConstants.ID_MANUFACTURER);
				rs = pre.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						ManuFacturer manuFacturer = new ManuFacturer();
						manuFacturer.setId(rs.getInt("Id"));
						manuFacturer.setName(rs.getString("Name"));
						listManuFacturer.add(manuFacturer);
					}
				}
			}

		} catch (SQLException e) {
			LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
		} finally {
			DbHelper.closeConnection(conn, pre, rs);
		}
		return listManuFacturer;
}

	@Override
	public int getCategoryId(String name) {
		int result = 0;
		conn = DbHelper.getConnection();
		if(conn != null) {
			try {
				pre = conn.prepareStatement(QueryConstants.GET_ID_CATEGORY);
				pre.setString(1, name);
				rs = pre.executeQuery();
				if(rs.next()) {
					result = rs.getInt("Id");
				}
			} catch (SQLException e) {
				LogUtils.getLogger(CategoryDaoImpl.class.getName()).error(e.getMessage());
			}
			
		}
		
		return result;
	}
	

}
