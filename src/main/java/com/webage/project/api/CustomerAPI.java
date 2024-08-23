package com.webage.project.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	//Implement a POST method that create a new customer
	@PostMapping
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer customer, UriComponentsBuilder uri){
		if (customer.getId() != 0 || customer.getName() == null || customer.getEmail() == null || customer.getPassword() == null) {
			return ResponseEntity.badRequest().build();
		}
		customer = repo.save(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customer.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}

	//Implement a PUT method that update the customer by customer id
	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(
			@RequestBody Customer customer,
			@PathVariable("customerId") long customerId) 
	{
		if (customer.getId() != customerId || customer.getName() == null || customer.getEmail() == null || customer.getPassword() == null) {
			return ResponseEntity.badRequest().build();
		}
		customer = repo.save(customer);
		return ResponseEntity.ok().build();
	}

	//Implement a GET method that returns a customer by username
	@GetMapping("/byname/{username}")
	public ResponseEntity<?> lookupCustomerByNameGet(@PathVariable("username") String username,
			UriComponentsBuilder uri) {
		
		java.util.Iterator<Customer> customers = repo.findAll().iterator();
		while(customers.hasNext()) {
			Customer cust = customers.next();
			if(cust.getName().equalsIgnoreCase(username)) {
				ResponseEntity<?> response = ResponseEntity.ok(cust);
				return response;				
			}			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	//Implement a DELETE method that delete a customer by customer id
	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") long id) {
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}