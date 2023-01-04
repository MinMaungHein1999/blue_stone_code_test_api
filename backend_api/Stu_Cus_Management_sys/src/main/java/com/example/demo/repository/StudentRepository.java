package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;



public interface StudentRepository extends JpaRepository<Student, Long>{
	@Query(   value = "SELECT * FROM Students s WHERE s.mail_status=:mailStatus", 
			  nativeQuery = true)
	List<Student> findAllStudentsByMailStatus(boolean mailStatus);
}
