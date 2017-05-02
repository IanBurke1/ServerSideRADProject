package com.sales.exceptions;

@SuppressWarnings("serial")
public class OrderException extends Exception {
	
	public OrderException(String message){
		super(message);
	}
}
