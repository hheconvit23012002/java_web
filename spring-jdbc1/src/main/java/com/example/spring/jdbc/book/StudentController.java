package com.example.spring.jdbc.book;

import java.io.IOException;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation. ResponseBody;

@Controller
public class StudentController {
	
	@GetMapping("/students") 
	public String getStudents(Model model) throws IOException {          
        List<Student> students = StudentDAO.getAll();              
        model.addAttribute("students",students);
		return "students";
	}
	
	@GetMapping("/student/{id}")
	public String getBook(Model model, @PathVariable String id) {
		model.addAttribute("id",id);		
		Student student = StudentDAO.get(id);		
		model.addAttribute("student",student);
		return "student-detail";
	}
	
	@PostMapping("/student/save/{id}")
	public String addBook(Model model,Student student, @PathVariable String id) throws IOException {		
		int result = StudentDAO.save(student);
		if(result == 200)
            return "redirect:/students";
		else if(result == 409) {
			model.addAttribute("student", student);
			model.addAttribute("id", -1);
			model.addAttribute("exist",1);
			return "student-detail";
		}
		else if(result == 408) {
			model.addAttribute("student", student);
			model.addAttribute("id", -1);;
			model.addAttribute("stExist",1);
			return "student-detail";
		}
		else
			return "error";
	}
	
	@PutMapping("/student/save/{id}")
	public String updateBook(Model model,Student student,@PathVariable String id) throws IOException {		
		int result = StudentDAO.update(student);
		if(result == 200)
            return "redirect:/students";
		else if(result == 408) {
			model.addAttribute("student", student);
			model.addAttribute("stExist",1);
			return "student-detail";
		}
		else
			return "error";
	}
	
	@PostMapping("/student/delete/{id}")
	public String deleteBook(@PathVariable String id) {				
		int result = StudentDAO.delete(id);
		if(result == 200)
            return "redirect:/students";
		else
			return "error";
	}
}
