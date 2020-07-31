package com.benz.security.test.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg)
    {
        super(msg);
    }
}
