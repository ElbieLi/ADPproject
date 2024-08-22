package com.webage.project.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webage.project.domain.Customer;
import com.webage.project.repository.CustomerRepository;

//Annotate the class as a controller REST operations
@RestController
//Annotate the class to handle all requests with a URL path with /customers"
@RequestMapping("/customers")
public class CustomerAPI{

	//Implement Spring dependency injection
	@Autowired
	//Create a variable of type CustomerRepository
	CustomerRepository repo;
	
	//Implement a GET method that returns a list of all Customer objects
	@GetMapping
	public Iterable<Customer> getAll(){
		return repo.findAll();
	}
	
	//Implement a GET method that returns a customer by id
	@GetMapping("/{customerId}")
	public Optional<Customer> getCustomerById(@PathVariable("customerId") long id){
		return repo.findById(id);
	}
	
	
}