package com.foundation4u.exception;

import java.io.Serializable;

public class EmployeeServiceException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5240685354358383834L;

	public EmployeeServiceException(){
		super();
	}
	
	public EmployeeServiceException(String message){
		super(message);
	}
}
