package com.trungtamjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.dao.UserDao;
import com.trungtamjava.entity.User;
import com.trungtamjava.model.UserDTO;
import com.trungtamjava.service.UserService;

@Service
// dat ctransaction, k dat vs controller vi k co y nghia j ca
@Transactional
// dat cho tat ca cac class, muon dat cho 1 class thi chi can dat tren adau cac classs

public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	public void addUser(UserDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setPhone(userDTO.getPhone());		
		userDao.addUser(user);
	}

	public void updateUser(UserDTO userDTO) {
		User user= userDao.getUserById(userDTO.getId());
		if(user != null){
		user.setName(userDTO.getName());
		user.setPhone(userDTO.getPhone());
		
		userDao.updateUser(user);	
		}
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		//userDao.deleteUser(id);
		
		User user = userDao.getUserById(id);
			if(user !=null){
				userDao.deleteUser(id);
			}
	}

	public UserDTO getUserById(int id) {
		// TODO Auto-generated method stub
		//return userDao.getUserById(id);
		
		User user = userDao.getUserById(id);
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setPhone(user.getPhone());
		return userDTO;
	}

	public List<UserDTO> getAllUsers() {
		
		List<User> users = userDao.getAllUsers();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for(User user: users){
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setPhone(user.getPhone());
			userDTOs.add(userDTO);
		}
		return userDTOs;
		// TODO Auto-generated method stub
		//return userDao.getAllUsers();
	}
}
