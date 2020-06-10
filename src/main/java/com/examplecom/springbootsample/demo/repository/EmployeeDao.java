package com.examplecom.springbootsample.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examplecom.springbootsample.demo.entity.*;





@Repository

public interface  EmployeeDao extends JpaRepository<Employee, Long> {

	@Query(value="SELECT u.employeeName  FROM Employee u WHERE u.location<= :location")
	public List<String> findByLocation(int location);
	
	
	
}
