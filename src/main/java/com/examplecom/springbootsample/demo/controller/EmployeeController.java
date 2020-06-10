package com.examplecom.springbootsample.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examplecom.springbootsample.demo.entity.Employee;
import com.examplecom.springbootsample.demo.exception.ResourceNotFoundException;
import com.examplecom.springbootsample.demo.repository.EmployeeDao;
import com.examplecom.springbootsample.demo.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RefreshScope
@RestController
@Api(value = "Employee Management System")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired

	EmployeeDao employeeDao;


	@ApiOperation(value = "This is a test API")
	@GetMapping(value="/")
	public String index() {

		return "This is an invalid URL";
	}


	@ApiOperation(value = "View a list of available Employees")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@GetMapping("/getEmployee")
	@HystrixCommand(commandKey = "employeelist", fallbackMethod = "reliable")
	public Collection<Employee> getAllEmployee() throws InterruptedException{
		//Thread.sleep(3000);
		return employeeDao.findAll();

	}

	public Collection<Employee> reliable() {



				
		Employee emp = new Employee();
		emp.setEmployeeName("Fall-back");;
		emp.setDepartment("Fall-back");
		emp.setLocation(0);

		emp.setEmployeeName("Fall-back-1");;
		emp.setDepartment("Fall-back-1");
		emp.setLocation(15);
		return (Collection<Employee>) emp;
	}

	// "Cloud Native Java (O'Reilly)";}

	@ApiOperation(value = "Create an employee")
	@PostMapping("/updateEmployee")
	public Employee createEmployee(

			@ApiParam(value = "Employee name, department & location parameters are required for creating new employee record", required = true) @RequestBody @Valid Employee employee) {


		return employeeDao.save(employee);


	} 

	@ApiOperation(value = "Get Employee Details through Employee ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PutMapping(value="/employee/{id}")
	private ResponseEntity<Employee> getEmployeeById(
			@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable(value="id") @Min(1) @Valid long employeeId)
					throws ResourceNotFoundException {

		Employee employee= employeeDao.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee doesn't exsist::"+ employeeId));


		return (ResponseEntity<Employee>) ResponseEntity.ok().body(employee);
	}


	@ApiOperation(value = "Get Employee Details based on Location")
	@ApiParam(value = "location from which employee object will be retrieved", required = true)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success|OK"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value="/employeebylocation")
	public List<String> getEmployeeByLocation(@Valid @RequestParam int locationId)
	{


		List<String> employee= (List<String>) employeeDao.findByLocation(locationId);	
		return employee;


	}

	//Sorting
	
	@GetMapping("/getEmployeesByFilters")
	public Object getEmployeesByFilter() {
		
		return employeeService.getEmployeeByFilters();
	}


	//Sum of Locations for all Employees using MapToDouble
	
	@GetMapping("/getEmployeeByLocation")
	public Object getEmployeeByLocation() {
		
		return employeeService.getEmployeeByLocation();
	}



}