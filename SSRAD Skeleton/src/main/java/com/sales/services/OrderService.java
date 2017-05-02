package com.sales.services;

/*
 * Ian Burke - G00307742
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.exceptions.OrderException;
import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.repositories.CustomerInterface;
import com.sales.repositories.OrderInterface;
import com.sales.repositories.ProductInterface;

@Service //Indicates that this class is a Service which can have business logic..
public class OrderService {
	// Tells the application context to inject an instance of OrderInterface, ProductInterface, CustomerInterface here..
	@Autowired
	private OrderInterface oi;
	@Autowired
	private ProductInterface pi;
	@Autowired
	private CustomerInterface ci;
	
	//Create object variables
	private Customer c;
	private Product p;
	
	//Creating date object to use for order dates
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Date date = new Date(); //current date
	
	//Puts Orders into array list 
	public ArrayList<Order> getAll() {
		
		//return the list which uses OrderInterface to get list of Orders
		return (ArrayList<Order>) oi.findAll();
	}
	
	
	public Order save(Order o) throws OrderException 
	{
		//CustomerId
		c = ci.findOne(o.getCust().getcId());
		//ProductId
		p = pi.findOne(o.getProd().getpId());
		
		//Throws exception if theres less quantity in stock than order quantity
		if (o.getQty() > p.getQtyInStock()) {
			throw new OrderException("Insufficient Stock ");
		} else {
		/*
		if (c == null) {
			//throw new NullCIdException("No Customer Id entered");
		} else if (p == null) {
			//throw new NullPIdException("No Product Id entered");
		} else if (o.getCust().getcId() != c.getcId()) {
		//	throw new NotCIdException("Customer Id doestnt exist: " + c.getcId());
		} else if (o.getProd().getpId() != p.getpId()) {
			//throw new NotPIdException("Product Id doestnt exist: " + p.getpId());
		} else 
		
		*/
			//Quantity in stock - order quantity
			p.setQtyInStock(p.getQtyInStock() - o.getQty());
			//set the time
			o.setOrderDate(dateFormat.format(date));
			
			//Customer name
			o.getCust().setcName(c.getcName());
			//Product description
			o.getProd().setpDesc(p.getpDesc());
			
			//Update the Order table in database
			oi.save(o);
			//return the order from interface
			return oi.save(o);
		}
	}
	
}
