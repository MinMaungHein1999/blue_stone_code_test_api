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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.Student;
import com.example.demo.excel.ExcelGeneratorUser;
import com.example.demo.service.UserServic;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.StudentService;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserServic userServic;
	
	public UserController(UserServic userServic) {
		super();
		this.userServic = userServic;
	}
	
	 @GetMapping("/export_users")
	    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=user" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);

	        List <User> listOfStudents = userServic.getAllUsers();
	        ExcelGeneratorUser generator = new ExcelGeneratorUser(listOfStudents);
	        generator.generateExcelFile(response);
	    }
	@GetMapping
	public List<User> listUsers(Model model) {
	
		return userServic.getAllUsers();
	}
	
	
	@PostMapping
	public User saveUser(@RequestBody User user) {
		user.setCreatedDate(new Date());
	   
		return userServic.saveUser(user);
	}
	

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		
		// get student from database by id
		User existingUser = userServic.getUserById(id);
		existingUser.setUserId(id);
		existingUser.setName(user.getName());
		existingUser.setPhoneNo(user.getPhoneNo());
		existingUser.setEmail(user.getEmail());
		// save updated student object
		
		return userServic.updateUser(existingUser);		
	}
	
	// handler method to handle delete student request
	
	@GetMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userServic.deleteUserById(id);
	}
	
}
