package com.examplecom.springbootsample.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 


@SpringBootApplication
@EnableHystrix
@EnableCircuitBreaker
@EnableJpaRepositories("com.examplecom.springbootsample.demo.repository")
public class SpringBootSampleApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootSampleApplication.class);
	   
	public static void main(String[] args) {
		logger.info("This is an info message");
		logger.warn("this is a warn message");
	      logger.error("this is a error message");
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}
	
	

	
}
