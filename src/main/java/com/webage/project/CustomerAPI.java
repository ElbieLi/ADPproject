package com.webage.project;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/customers")
public class CustomerAPI{
	
	ArrayList<Customer> customerList;
	
	public CustomerAPI() {
		Customer customer1 = new Customer(1, "Elbie", "elbie.li@adp.com");
		Customer customer2 = new Customer(2, "Mochi", "mochi@adp.com");
		Customer customer3 = new Customer(3, "Life", "life@adp.com");
		
		customerList = new ArrayList<Customer>();
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		
	}
	
	@GetMapping("api")
	public String greeting(){
		return "Welcome to my page!";
	}
	
	@GetMapping("api/customers")
	public String getAll(){
		String result = "";
		for(int i = 0; i < customerList.size(); i++) {
			result += customerList.get(i).toString() + System.lineSeparator();
		}
		return result;
	}

	@GetMapping("api/customers/{id}")
	public String getCustomerByID(@PathVariable("id") long id) {
		String result = "";
		for(int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getId() == id) {
				result += customerList.get(i).toString();
				return result;
			}
		}
		return "No such customer";
	}
	
}