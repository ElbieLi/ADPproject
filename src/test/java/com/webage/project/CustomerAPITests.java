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
        Customer customer1 = new Customer(1, "Amy", "amy@adp.com", "pw123");

        Customer customer2 = new Customer(1, "John", "john@adp.com", "pw123");

        when(repo.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        Iterable<Customer> result = customerAPI.getAll();

        assertEquals(Arrays.asList(customer1, customer2), result);
    }



}
