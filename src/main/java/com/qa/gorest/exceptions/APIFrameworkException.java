package com.qa.gorest.exceptions;

public class APIFrameworkException extends RuntimeException{

    public APIFrameworkException(String message){
        super(message);
    }
    public APIFrameworkException(String message, Throwable cause){
        super(message, cause);
    }
}
