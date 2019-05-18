package com.fsoft.project.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fsoft.project.dao.RegisterDao;
import com.fsoft.project.db.DbHelper;
import com.fsoft.project.entity.Member;
import com.fsoft.project.security.Sha1Security;
import com.fsoft.project.utils.constants.QueryConstants;

public class RegisterDaoImpl implements RegisterDao{

	PreparedStatement ps=null;
	CallableStatement cs=null;
	ResultSet rs=null;
	Connection conn=null;
	
	@Override
	public int registerMember(Member member){
		int result=0;
		try {
			conn=DbHelper.getConnection();
			if(conn!=null) {
				ps = conn.prepareStatement(QueryConstants.REGISTER_MEMBER);
				ps.setString(1, member.getFirstName());
				ps.setString(2, member.getLastName());
				ps.setString(3, member.getAddress());
				ps.setString(4, member.getPhone());
				ps.setBoolean(5, false);
				ps.setString(6, member.getEmail());
				ps.setString(7, Sha1Security.SHA1(member.getPassword()));
				result=ps.executeUpdate();
			}

		} catch (SQLException e) {
		} finally {
			DbHelper.closeConnection(conn, ps);
		}

		return result;
	}

	@Override
	public boolean checkEmail(Member member){
		boolean status = false;
		conn=DbHelper.getConnection();
		if(conn!=null) {
			try {
				ps=conn.prepareStatement(QueryConstants.CHECK_EMAIL_EXISTS);
				ps.setString(1, member.getEmail());
				rs=ps.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("Email").equals(member.getEmail())) {
						status=true;
					}else {
						status=false;
					}
				}
			} catch (SQLException e) {
			}
		}
		return status;

	}

}
