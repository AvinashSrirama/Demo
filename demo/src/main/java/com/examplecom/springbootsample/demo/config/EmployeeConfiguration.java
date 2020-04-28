package com.examplecom.springbootsample.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.examplecom.springbootsample.demo.repository")
public class EmployeeConfiguration {

}
