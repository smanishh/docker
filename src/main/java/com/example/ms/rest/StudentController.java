package com.example.ms.rest;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ms.model.Course;
import com.example.ms.service.StudentService;



@RestController
@RequestMapping("/rest")
public class StudentController {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private StudentService studentService;

	@GetMapping
	public String hello(HttpServletRequest req) {
		System.out.println("role1: " + req.isUserInRole("USER"));
		System.out.println("role2: " + req.isUserInRole("ADMIN"));
		return "Hello Manish..";		
	}
	
	@GetMapping("/students/{studentId}/courses")
	public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
		return studentService.retrieveCourses(studentId);
	}
	
	
	@RequestMapping(value = "/template/products")
	   public String getProductList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
	   }
	
	@RequestMapping(value = "/template/products2")
	   public String createProducts() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8080/products", HttpMethod.POST, entity, String.class).getBody();
	   }
	
}
