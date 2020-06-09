package com.examplecom.springbootsample.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplecom.springbootsample.demo.entity.Employee;
import com.examplecom.springbootsample.demo.repository.EmployeeDao;


@Service
public class EmployeeService {
	
	
	@Autowired
	EmployeeDao employeeDao;
	

	public  Object getEmployeeByFilters(){
		
		List<Employee> empList= employeeDao.findAll();
		
		List<Predicate<Employee>> allPredicates= new ArrayList<Predicate<Employee>>();
		allPredicates.add(e -> e.getEmployeeName().startsWith("R"));
		allPredicates.add(e -> e.getEmployeeName().contains("i"));
		allPredicates.add(e -> e.getLocation() > 10);
		
		List<Employee> sortList= (List<Employee>) empList.stream().filter(allPredicates.stream().reduce(x-> true, Predicate::and))
								.collect(Collectors.toList());
		
		System.out.println("The Sorted list consists of:"+ sortList);
		return sortList;
	}
		
		public Object getEmployeeByLocation() {
			
			List<Employee> empList= employeeDao.findAll();
			
			double totalLocations= empList.stream().mapToDouble(Employee::getLocation).sum();
			
			
			System.out.println("Total count of location:"+ totalLocations);
			return totalLocations;
		
	}
	
	
}
