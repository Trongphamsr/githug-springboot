package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("SELECT e FROM User e WHERE e.username = :username AND e.password = :password")
	List<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	@Query("SELECT e FROM User e WHERE e.username = :username")
	User findByUsername(@Param("username") String username);
}
