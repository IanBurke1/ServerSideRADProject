package com.sales.services;

/*
 * Ian Burke - G00307742
 */

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.repositories.CustomerInterface;

@Service //Indicates that this class is a Service which can have business logic..
public class CustomerService {
	
	// Tells the application context to inject an instance of CustomerInterface here..
	@Autowired
	private CustomerInterface cs;
	
	//Save method passing in Customer object
	public Customer save(Customer customer)
	{
		//save() method updates a table with a specified entity (product)
		return cs.save(customer);
	}
	
	public ArrayList<Customer> getAll()
	{
		//return the list which uses CustomerInterface to get list of customers
		return (ArrayList<Customer>) cs.findAll();
	}
	
	
}
