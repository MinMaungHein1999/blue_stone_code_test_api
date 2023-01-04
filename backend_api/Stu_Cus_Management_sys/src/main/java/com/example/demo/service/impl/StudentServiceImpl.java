package com.example.demo.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
    private EmailSenderService  emailSenderService;
	private StudentRepository studentRepository;
	
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);	
	}

	@Override
	public List<Student> getAllStudentsByStatus() {
	
		return studentRepository.findAllStudentsByMailStatus(false);
	}
	@Scheduled(fixedRate = 5000)
	public void getAllUsersCountScheduled() {
		List<Student> students=studentRepository.findAllStudentsByMailStatus(false);
		System.out.println("ScheduledGetAllStudentCount="+students.size());
		for(Student s : students) {
			emailSenderService.sendSimpleEmail(s.getEmail(),"Student Manaement System","Thank For your registration!");
			s.setMailStatus(true);
			updateStudent(s);
			
		}
		
		
	}

}
