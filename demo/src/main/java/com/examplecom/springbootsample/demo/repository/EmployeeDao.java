package com.examplecom.springbootsample.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examplecom.sprongbootsample.demo.entity.*;

@Repository
public interface  EmployeeDao extends JpaRepository<Employee, Long> {

}
