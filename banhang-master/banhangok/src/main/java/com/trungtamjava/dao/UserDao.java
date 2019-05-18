package com.trungtamjava.dao;

import java.util.List;

import com.trungtamjava.entity.User;

public interface UserDao {

	// viet ham them sua xoa nguoi dung
	
//	public void addUser(UserDTO user);
//	public void updateUser(UserDTO user);
//	public void deleteUser(int id);
//	public UserDTO getUserById(int id);
//	public List<UserDTO> getAllUsers();
	
	
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public User getUserById(int id);
	public List<User> getAllUsers();
	
}
