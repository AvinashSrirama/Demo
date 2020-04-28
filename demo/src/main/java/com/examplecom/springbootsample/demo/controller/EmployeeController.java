package com.examplecom.springbootsample.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.examplecom.springbootsample.demo.repository.EmployeeDao;
import com.examplecom.sprongbootsample.demo.entity.Employee;

import antlr.collections.List;

@Controller
public class EmployeeController {

	
	
	@Autowired
	
	EmployeeDao employeeDao;
	
 
 
 @GetMapping(value="/")
 public String index() {
	 
	 return "This is an invalid URL";
 }
 


@GetMapping("/getEmployee")
public List getAllEmployee(){
	
	return (List) employeeDao.findAll();
	
}

@PostMapping("/updateEmployee")
public Employee createEmployee(@RequestBody Employee employee) {
	return employeeDao.save(employee);
	
	
}

}

	
	





