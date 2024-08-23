package com.webage.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.webage.project.api.CustomerAPI;
import com.webage.project.domain.Customer;
import com.webage.project.repository.CustomerRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerAPITests {

    @Mock
    private CustomerRepository repo;
    
    @InjectMocks
    private CustomerAPI customerAPI;

    @BeforeEach
    //initialize Mockito annotations
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        //mock the behavior of repo.findAll()
        Customer customer1 = new Customer(1, "Amy", "amy@adp.com", "pw123");

        Customer customer2 = new Customer(1, "John", "john@adp.com", "pw123");

        //when repo.finall() is called, return a list of customers
        when(repo.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //call the method under test
        Iterable<Customer> result = customerAPI.getAll();

        //use assert to verify the result
        assertEquals(Arrays.asList(customer1, customer2), result);
    }

    @Test
    public void testGetCustomerById(){
        Customer customer = new Customer(1, "Amy", "amy@adp.com", "pw123");

        when(repo.findById((long) 1)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerAPI.getCustomerById((long)1);

        assertEquals(Optional.of(customer), result);

    }


}
