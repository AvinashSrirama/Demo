package com.examplecom.springbootsample.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplecom.springbootsample.demo.repository.EmployeeDao;


@Service
public class EmployeeService {
	
	
	@Autowired
	EmployeeDao employeeDao;
	

	
	
	
}
