package com.epam.testapp.database.connection;

import com.epam.testapp.exception.TestappException;

public class ConnectionPoolTestappException extends TestappException{

	private static final long serialVersionUID = 1L;

	public ConnectionPoolTestappException( String message, Exception e ) {
		super( message, e );
	}
	
	public ConnectionPoolTestappException( String message ) {
		super( message );
	}

}
