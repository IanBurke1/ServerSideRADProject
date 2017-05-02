package com.sales.controllers;

/*
 * Ian Burke - G00307742
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.services.CustomerService;

@Controller //Indicates that this class is a controller
public class CustomerCtrl {
	
	// Tells the application context to inject an instance of CustomerService here..
	@Autowired
	private CustomerService cs;
	
	//HttpServlet is an abstract class that implements the Servlet interface and...
	//is specially designed to handle HTTP requests. 
	//HttpServletRequest provides request methods such as GET POST.
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value = "/showCustomers", method = RequestMethod.GET)
	public String getCustomers(Model m) {

		//gets all the customers from customer and put them into array list
		ArrayList<Customer> customers = cs.getAll();
		//loop through each order in the list
		for (Customer c1 : customers) {
			System.out.println("Cid=" + c1.getcId());
			for (Order o1 : c1.getOrders()) {
				System.out.println("Oid=" + o1.getoId());
			}
		}
		//Add customers to jsp page using "customers" tag to call the list
		m.addAttribute("customers", customers);
		//Return the name of the jsp page to be displayed
		return "ShowCustomers";
	}
	
	/*
	 * @ModelAttribute is used to send data to or retrieve data from, a controller (ProductCtrl), when the data is bound to, or represented by, an Object. 
	 * @ModelAttribute on a method argument indicates the argument will be retrieved from the model (Product).
	 * 	Add an @ModelAttribute annotation to the method specifying a name with which to reference the object in the view
	 */
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String postCustomer(@Valid @ModelAttribute("customer1") Customer c, BindingResult result, HttpServletRequest h, Model m)
	{
		if(result.hasErrors()) {
			//Return the name of the jsp page to be displayed
			return "addCustomer";
		} else {
			System.out.println("HTTP Request = " + h.getMethod());

			cs.save(c);
			//gets all the customers from customer and put them into array list
			ArrayList<Customer> customers = cs.getAll();
			//loop through each order in the list
			for (Customer c1 : customers) {
				System.out.println("Cid=" + c1.getcId());
				for (Order o1 : c1.getOrders()) {
					System.out.println("Oid=" + o1.getoId());
				}
			}
			//Add customers to jsp page using "customers" tag to call the list
			m.addAttribute("customers", customers);
			//Return the name of the jsp page to be displayed
			return "ShowCustomers";
		}
	}
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value="/addCustomer", method=RequestMethod.GET)
	public String getCustomer(@ModelAttribute("customer1") Customer c, HttpServletRequest h)
	{
		//Return the name of the jsp page to be displayed
		return "addCustomer";
		
	}

	

}
