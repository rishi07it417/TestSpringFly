package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(path = "/student")
public class StudentController {
	@Autowired
	private StudentService studentservice;
	
	@GetMapping("/getStudents")
	public List<Student> getStudents() {
		return studentservice.getAllStudents();
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		return studentservice.addStudent(student);
	}
	
	@GetMapping("/getStudentByName/{studentName}")
	public List<Student> getStudents(@PathVariable String studentName ) {
		return studentservice.findStudentByCriteria(studentName);
	}


}
