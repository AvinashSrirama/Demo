package com.examplecom.springbootsample.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.client.model.Author;
import io.swagger.models.Response;

@Service
public class AuthorService {

	RestTemplate restTemplate= new RestTemplate();

	public final static String REST_SERVICE_URI = "http://localhost:8080/sample";
	
	//Get Authors List
	public List<Author> getAllAuthors() {	
	Author[] list=  (Author[]) restTemplate.getForObject(REST_SERVICE_URI+"/getAllAuthors", Object[].class);
	List<Author> searchList = Arrays.asList(list);
			
	return  searchList;
	

}
	    
	//Create a new Author
	public Object createAuthor(@RequestBody Author author) {
		
		
		
		Author newAuthor= new Author();
		newAuthor.setFirstName("Ellery");
		newAuthor.setLastName("Adams");
		
		RestTemplate restTemplate= new RestTemplate();
		
		Object create1= restTemplate.postForObject(REST_SERVICE_URI+"/author", newAuthor, Object.class);
		
	
		return create1;
	}
	
	
		//Update Author
	public Object updateAuthor(@RequestBody Author author) {
		
		Author updateAuthor = new Author();
		  updateAuthor.firstName("Ellery");	
		  updateAuthor.setLastName("Sam");
		  RestTemplate restTemplate = new RestTemplate();
		  restTemplate.put(REST_SERVICE_URI+"/updateAuthor/1", updateAuthor);
		  Object updatedAuthor = restTemplate.getForObject(REST_SERVICE_URI+"getAllAuthors", Object.class);
		  System.out.println("Updated Author: " +updatedAuthor);
		return updatedAuthor;
		
	}
	
	//Delete Author
	public Object deleteAuthor(@RequestBody Author author) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(REST_SERVICE_URI+"/deleteAuthor/6");
		return restTemplate;
	}
	
	
	//Sort Authors
	
	public List<Author> getAuthorbyFirstName() {	
		
		Author[] list=  restTemplate.getForObject(REST_SERVICE_URI+"/getAllAuthors", Author[].class);
		List<Author> searchList = Arrays.asList(list);
		
		System.out.println("The sorted list is: ");
		
		List<Author> sortedAuthors= searchList.stream().sorted(Comparator.comparing(Author::getFirstName)).collect(Collectors.toList());
		return  sortedAuthors;
	}
		
		
		//Sort Authors by Books
		
		public List<Author> getAuthorByNames(){
		
		
			
			Author[] list1=  restTemplate.getForObject(REST_SERVICE_URI+"/getAllAuthors", Author[].class);
			List<Author> searchList1 = Arrays.asList(list1);
			
			System.out.println("Sorted Authors list based on Books:");
			
			
			Predicate<Author> p1= auth->  auth.getFirstName().startsWith("A");
			Predicate<Author> p2= auth-> auth.getLastName().length()< 4;
			
			List<Author> sortedList = searchList1.stream().filter(p1.or(p2)).collect(Collectors.toList());
			
			
			boolean b1= searchList1.stream().allMatch(p1);
			boolean b2= searchList1.stream().noneMatch(p1);
			
			return sortedList;
			
			
			
		}
		
		

		
	
}