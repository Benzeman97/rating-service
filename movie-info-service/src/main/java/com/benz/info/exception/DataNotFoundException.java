package com.benz.info.exception;

public class DataNotFoundException extends RuntimeException{


	private static final long serialVersionUID = -2066229227456301558L;
	
	public DataNotFoundException(String msg)
	{
		super(msg);
	}

}
