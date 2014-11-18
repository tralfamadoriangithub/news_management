package com.epam.testapp.database;

import com.epam.testapp.exception.TestappException;

public class ConnectionPoolTestappException extends TestappException{

	public ConnectionPoolTestappException( String message, Exception e ) {
		super( message, e );
	}
	
	public ConnectionPoolTestappException( String message ) {
		super( message );
	}

}
