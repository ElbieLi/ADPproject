package com.webage.project.repository;

import org.springframework.data.repository.CrudRepository;
import com.webage.project.domain.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long>{
	
}
