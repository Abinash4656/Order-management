package com.ordermanagement.orderitem.exception;
/*
 * Exception indicates unavailability of resource when there is no item available
 * while quering the table
 * */
public class ItemNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException(String message) {
		super(message);
	}

}
