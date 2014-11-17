package com.epam.testapp.database;

import com.epam.testapp.exception.TestappException;

public class DaoTestappException extends TestappException{

	private static final long serialVersionUID = 1L;

	public DaoTestappException( String message, Exception e ) {
		super( message, e );
	}

	public DaoTestappException( String message ) {
		super( message );
	}
}
