package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entities.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.result.ServiceResult;

@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserRepository  userRepository;
	
	public User loadUserByUsername(final String username) {
		return  userRepository.findByUsername(username);
//		return userDao.loadUserByUsername(username);
	}
	

	
	public ServiceResult fillAll() {
		ServiceResult result = new ServiceResult();
		result.setData(userRepository.findAll());
		return result;
	}
	
	public List<User> checkLogin(User userForm) {
		return userRepository.findByUsernameAndPassword(userForm.getUsername(), userForm.getPassword());
//		if(!list.isEmpty()) {
//			return list;
//		}else {
//			return null;
//		}
//		return userDao.checkLogin(userForm);
		
		 
	}
}
