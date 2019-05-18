package com.fsoft.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fsoft.project.dao.MemberDao;
import com.fsoft.project.db.DbHelper;
import com.fsoft.project.entity.Member;
import com.fsoft.project.security.Sha1Security;
import com.fsoft.project.utils.constants.QueryConstants;

public class MemberDaoImpl implements MemberDao {
	 private Connection connection;
	  private PreparedStatement ps;
	  private ResultSet rs;
	  @Override
	  public Member getMember(String email, String password) {
	    Member member = null;
	    connection = DbHelper.getConnection();
	    if(connection == null) return null;
	    try {
	      ps = connection.prepareStatement(QueryConstants.GET_MEMBER);
	      ps.setString(1, email);
	      ps.setString(2, Sha1Security.SHA1(password));
	      rs = ps.executeQuery();
	      if(rs.next()) {
	        member = new Member();
	        member.setId(rs.getInt("Id"));
	        member.setEmail(rs.getString("Email"));
	        member.setRole(rs.getBoolean("Role"));
	        member.setFirstName(rs.getString("Firstname"));
	        member.setLastName(rs.getString("LastName"));
	        member.setAddress(rs.getString("Address"));
	        member.setPhone(rs.getString("Phone"));
	        member.setPassword(rs.getString("Password"));
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }finally {
	    	DbHelper.closeConnection(connection, ps, rs);
	    }
	    return member;
	  }
	@Override
	public int updateMember(Member member) {
		int affected = 0;
		connection = DbHelper.getConnection();
		if(connection != null) {
			try {
				ps = connection.prepareStatement(QueryConstants.UPDATE_MEMBER);
				ps.setString(1, member.getFirstName());
				ps.setString(2, member.getLastName());
				ps.setString(3,member.getAddress());
				ps.setString(4, member.getPhone());
				ps.setString(5, member.getEmail());
				ps.setString(6, member.getPassword());
				ps.setInt(7, member.getId());
				affected = ps.executeUpdate();
			} catch (SQLException e) {
				affected = 0;
				e.printStackTrace();
			}
		}
		return affected;
	}
}
