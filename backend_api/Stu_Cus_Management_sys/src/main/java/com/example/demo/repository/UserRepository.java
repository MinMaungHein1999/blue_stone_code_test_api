package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByPhoneNo(String phoneNo);
	@Query(   value = "SELECT COUNT(*) FROM users", 
			  nativeQuery = true)
	int findAllUsersCount();
	@Query(   value = "SELECT * FROM users", 
			  nativeQuery = true)
	List<User> findAllUsers();
	
	@Query(   value = "SELECT * FROM users u WHERE u.mail_status=:mailStatus", 
			  nativeQuery = true)
	List<User> findAllUsersByMailStatus(boolean mailStatus);
	
}
