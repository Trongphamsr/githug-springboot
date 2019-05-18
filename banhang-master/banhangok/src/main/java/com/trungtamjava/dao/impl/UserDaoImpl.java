package com.trungtamjava.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.UserDao;
import com.trungtamjava.entity.User;

@Repository
@Transactional

public class UserDaoImpl implements UserDao{

	@Autowired
	SessionFactory sessionFactory;
	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
	
	public void addUser(User user) {
		
		sessionFactory.getCurrentSession().save(user);
		
//		String sql="INSERT INTO users(TEN_KH,SO_DT) VALUES(?,?)";
//		jdbcTemplate.update(sql,user.getName(), user.getPhone());
	}

	public void updateUser(User user) {
//		String sql="UPDATE users SET TEN_KH= ?, SO_DT= ?,WHERE id= ?";
//		jdbcTemplate.update(sql,user.getName(),user.getPhone(),user.getId());
		
		sessionFactory.getCurrentSession().merge(user);
	}

	public void deleteUser(int id) {
//		String sql="DELETE FROM users WHERE id=?";
//		jdbcTemplate.update(sql,id);
		
		sessionFactory.getCurrentSession().delete(getUserById(id));
		
	}

	public User getUserById(int id) {
		
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
		
		
//		String sql="SELECT * FROM users WHERE id=?";
//		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<UserDTO>(){
//
//			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				UserDTO user = new UserDTO();
//				user.setId(rs.getInt("id"));
//				user.setName(rs.getString("TEN_KH"));
//				user.setPhone(rs.getInt("SO_DT"));
//				return user;
//			}
//			
//		});
		
	}

	public List<User> getAllUsers() {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	
//		String sql = "SELECT * FROM users";
//		return jdbcTemplate.query(sql,new Object[]{},new  RowMapper<UserDTO>(){
//			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {			
//				UserDTO user = new UserDTO();
//				user.setId(rs.getInt("id"));
//				user.setName(rs.getString("TEN_KH"));
//				user.setPhone(rs.getInt("SO_DT"));
//				return user;
//			}			
//		});
	}
		
}