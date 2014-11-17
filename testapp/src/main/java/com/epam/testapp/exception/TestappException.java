package com.epam.testapp.exception;

public class TestappException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private Exception hiddenException;
	
	public TestappException( String message ){
		super( message );
	}

	public TestappException( String message, Exception e){
		super(message);
		hiddenException = e;
	}

	public Exception getHiddenException() {
		return hiddenException;
	}
}
