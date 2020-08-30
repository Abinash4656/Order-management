package com.ordermanagement.orderitem.exception;
/*
 * exception in case of any problem while saving items
 * */
public class SaveItemException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	public SaveItemException(String message) {
		super(message);
	}

}
