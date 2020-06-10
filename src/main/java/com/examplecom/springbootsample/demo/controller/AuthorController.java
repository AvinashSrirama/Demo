package com.examplecom.springbootsample.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.examplecom.springbootsample.demo.entity.Employee;
import com.examplecom.springbootsample.demo.service.AuthorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.swagger.client.model.Author;

@RefreshScope
@RestController
public class AuthorController {

	
	@Autowired
	private AuthorService authorService;
	
	RestTemplate restTemplate= new RestTemplate();
	
	
	public final String REST_SERVICE_URI = "http://localhost:8080/sample";
	
	/* GET 
	@SuppressWarnings("unused")
	private static void listAllUsers(){
	System.out.println("Testing listAllAuthors API-----------");
	
	
	@SuppressWarnings("unchecked")
	List<LinkedHashMap<String, Object>> usersMap = (List<LinkedHashMap<String, Object>>) restTemplate.getForObject(REST_SERVICE_URI+"/getAllAuthors/", Author.class);
	
	if(usersMap!=null){
	for(LinkedHashMap<String, Object> map : usersMap){
	System.out.println("Author : id="+map.get("id")+", FirstName="+map.get("firstName")+",  LastName="+map.get("lastName"));}
}
	else
		System.out.println("No Author exists");
	}*/
	
	

	@GetMapping(value="/getAllAuthors")
	@HystrixCommand(commandKey = "authorlist", fallbackMethod = "reliable")
	public   List<Author> getAuthors() {
		
	
	return authorService.getAllAuthors();
	}
	
	public List<Author> reliable() {

		Author emp = new Author();
		emp.setFirstName("Ram");
		emp.setLastName("Talluri");

		Author emp2= new Author();	
		emp2.setFirstName("Sam");
		emp2.setLastName("Albert");

		List<Author> list= new ArrayList<>();
		list.add(emp);
		list.add(emp2);

		//List<Author> sortedList= list.stream().sorted((o1,o2)-> o1.getLastName().compareTo(o2.getLastName())).collect(Collectors.toList());

		List<Author> sortedList= list.stream().sorted(Comparator.comparing(Author::getLastName)).collect(Collectors.toList());

		return  sortedList;


	}

	@GetMapping(value="/author")
	public Object createAuthor(@RequestBody Author author) {
		
		return authorService.createAuthor(author);
	}
	
	
	@PutMapping(value="/updateAuthor/1")
	public Object updateAuthor(@RequestBody Author author){
		return authorService.updateAuthor(author);
		
		
	}
	
	@DeleteMapping("/deleteAuthor/{authorId}")
	public Object deleteAuthor(@RequestBody Author author) {
		
		return authorService.deleteAuthor(author);
	}

//Sort Authors based on First Names
	@GetMapping("/getAuthorByName")
	public List<Author> getAuthorByName() {
		return authorService.getAuthorbyFirstName();
	}
	
	
	
	//Sort Authors based on Book titles
	
	@GetMapping("/getAuthorsByNames")
	public List<Author> getAuthorByNames(){
		return authorService.getAuthorByNames();
		
		
	}
	}
		
//	@GetMapping("/deletAuthor")
//	public Author deleteAuthorbyId(@PathVariable(value="authorId")Long authorId) {
//		
//		return authorService.deleteAuthor(authorId);
//	}
//	}
	



