package com.examplecom.springbootsample.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.mapstruct.Mapper;

@Mapper
public class EmployeeDto {

	
	private long id;
	
	
	@NotEmpty(message="Please provide employee name")
	//only strings
	private String employeeName;
	
	@NotEmpty(message="Department cannot be empty")
	//only characters
	private String department;
	
	public EmployeeDto(long id, @NotEmpty(message = "Please provide employee name") String employeeName,
			@NotEmpty(message = "Department cannot be empty") String department,
			@NotNull(message = "Location field cannot be Null") int location) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.department = department;
		this.location = location;
	}

	@NotNull(message="Location field cannot be Null")
	//Only numbers
	private int location;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

}
