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

import com.sales.models.Product;
import com.sales.services.ProductService;

@Controller //Indicates that this class is a controller
public class ProductCtrl {
	
	// Tells the application context to inject an instance of ProductService here..
	@Autowired
	private ProductService ps;
	
	//HttpServlet is an abstract class that implements the Servlet interface and...
	//is specially designed to handle HTTP requests. 
	//HttpServletRequest provides request methods such as GET POST.
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods.
	@RequestMapping(value = "/showProducts", method = RequestMethod.GET)
	public String showProduct(Model m) {
		
		//gets all the products from product and put them into array list
		ArrayList<Product> products = ps.getAll();
		//loop through each order in the list
		for (Product p1 : products) {
			System.out.println("name=" + p1.getpId());
		}
		//Add products to jsp page using "products" tag to call the list
		m.addAttribute("products", products);
		//Return the name of the jsp page to be displayed
		return "ListProducts";
	}
	
	/*
	 * @ModelAttribute is used to send data to or retrieve data from, a controller (ProductCtrl), when the data is bound to, or represented by, an Object. 
	 * @ModelAttribute on a method argument indicates the argument will be retrieved from the model (Product).
	 * 	Add an @ModelAttribute annotation to the method specifying a name with which to reference the object in the view
	 */
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getProduct(@ModelAttribute("product1") Product p, HttpServletRequest h) 
	{
		System.out.println("HTTP Request = " + h.getMethod());
		//Return the name of the jsp page to be displayed
		return "addProduct";
	}
	
	//RequestMapping annotation is used to map web requests onto specific handler classes and/or handler methods
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String postProduct(@Valid @ModelAttribute("product1") Product p, BindingResult result, HttpServletRequest h, Model m) 
	{

		if (result.hasErrors()) 
		{
			//Return the name of the jsp page to be displayed
			return "addProduct";

		} else {

			System.out.println("HTTP Request = " + h.getMethod());

			ps.save(p);
			//gets all the products from product and put them into array list
			ArrayList<Product> products = ps.getAll();
			//loop through each order in the list
			for (Product p1 : products) {
				System.out.println("name=" + p1.getpId());
			}
			//Add products to jsp page using "products" tag to call the list
			m.addAttribute("products", products);
			//Return the name of the jsp page to be displayed
			return "ListProducts";
		}
	}

}
