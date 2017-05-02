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

import com.sales.exceptions.OrderException;
import com.sales.models.Order;
import com.sales.services.OrderService;

@Controller //Indicates that this class is a controller
public class OrderCtrl {
	
	// Tells the application context to inject an instance of OrderService here..
	@Autowired
	private OrderService os;
	
	//HttpServlet is an abstract class that implements the Servlet interface and...
	//is specially designed to handle HTTP requests. 
	//HttpServletRequest provides request methods such as GET POST.
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value="/showOrders", method = RequestMethod.GET)
	public String showOrder(Model m)
	{
		//gets all the orders from service and put them into array list
		ArrayList<Order> orders = os.getAll();
		//loop through each order in the list
		for (Order order1 : orders){
			System.out.println("OrderID: " + order1.getoId());
		}
		//Add orders to jsp page using "orders" tag to call the list
		m.addAttribute("orders", orders);
		//Return the name of the jsp page to be displayed
		return "ListOrders";
	}
	
	/*
	 * @ModelAttribute is used to send data to or retrieve data from, a controller (ProductCtrl), when the data is bound to, or represented by, an Object. 
	 * @ModelAttribute on a method argument indicates the argument will be retrieved from the model (Product).
	 * 	Add an @ModelAttribute annotation to the method specifying a name with which to reference the object in the view
	 */
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public String postOrder(@Valid @ModelAttribute("order1") Order o, BindingResult result, HttpServletRequest h, Model m)
	{
		
		if (result.hasErrors()) {
			//Return the name of the jsp page to be displayed
			return "addOrder";

		} else {
			try{
			//updates the order table in the database
			os.save(o);
			//gets all the orders from service and put them into array list
			ArrayList<Order> orders = os.getAll();
			//loop through each order in the list
			for (Order o1 : orders) {
				System.out.println("Orderid=" + o1.getoId());
			}
			//Add orders to jsp page using "orders" tag to call the list
			m.addAttribute("orders", orders);
			//Return the name of the jsp page to be displayed
			return "ListOrders";
		}
		
		catch(OrderException e){
			e.printStackTrace();
			return "Quantity Error";
		}
		
		}//else end
		
		
	}//postOrder end
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value = "/addOrder", method = RequestMethod.GET)
	public String getOrder(@ModelAttribute("order1") Order o, HttpServletRequest h) {
		System.out.println("HTTP Request = " + h.getMethod());
		//Return the name of the jsp page to be displayed
		return "addOrder";
	}
	
}
