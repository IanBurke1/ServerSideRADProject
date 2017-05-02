package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sales.models.Product;
import com.sales.repositories.ProductInterface;
/*
 * Ian Burke - G00307742
 */

@Service //Indicates that this class is a Service which can have business logic..
public class ProductService {
	
	// Tells the application context to inject an instance of ProductInterface here..
	@Autowired
	private ProductInterface pi;
	
	//Save method passing in Product object
	public Product save(Product product) {
		
		//save() method updates a table with a specified entity (product)
		return pi.save(product);
	}
	
	//Puts Product object into array list
	public ArrayList<Product> getAll() {
		//return the list which uses ProductInterface to get list of products
		return (ArrayList<Product>) pi.findAll();
	}

}