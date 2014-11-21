package com.epam.testapp.service;

import com.epam.testapp.exception.TestappException;

public class ServiceTestappException extends TestappException{

	private static final long serialVersionUID = 1L;

	public ServiceTestappException( String message, Exception e ) {
		super( message, e );
	}
	
	public ServiceTestappException( String message ) {
		super( message );
	}

}
