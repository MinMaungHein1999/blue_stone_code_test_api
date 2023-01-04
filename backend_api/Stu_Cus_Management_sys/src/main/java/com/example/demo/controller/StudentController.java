package com.example.demo.controller;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.Student;
import com.example.demo.excel.ExcelGeneratorUser;
import com.example.demo.excel.ExcelGeneratorStudent;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.StudentService;



@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	@GetMapping("/export_students")
    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <Student> listOfStudents = studentService.getAllStudents();
        ExcelGeneratorStudent generator = new ExcelGeneratorStudent(listOfStudents);
        generator.generateExcelFile(response);
       
    }
	
	
	// handler method to handle list students and return mode and view
	@GetMapping
	public List<Student> listStudents(Model model) {
		return	studentService.getAllStudents();
	}
	
	
	@PostMapping
	public Student saveStudent(@RequestBody Student student) {
		Student s=studentService.saveStudent(student);
		return s;
	}
	
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id, Model model) {
		return studentService.getStudentById(id);
	}

	@PatchMapping("/{id}")
	public Student updateStudent(@PathVariable Long id,@RequestBody Student student) {
		
		// get student from database by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setName(student.getName());
		existingStudent.setEmail(student.getEmail());
		
		// save updated student object
		return studentService.updateStudent(existingStudent);		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	
}
