package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	

}
