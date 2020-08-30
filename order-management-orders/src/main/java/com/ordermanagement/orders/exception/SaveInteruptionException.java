package com.ordermanagement.orders.exception;

import javax.validation.ConstraintViolationException;

public class SaveInteruptionException extends ConstraintViolationException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveInteruptionException() {
		super(null);
	}
}
