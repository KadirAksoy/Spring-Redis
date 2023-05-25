package com.kadiraksoy.SpringRedis.exception;

public class EmployeeExitsException extends RuntimeException{

    public EmployeeExitsException(String message){
        super(message);
    }
}
