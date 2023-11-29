package com.subh.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){

        super("Resource Not Found exception !!");
    }

    public ResourceNotFoundException(String message){

        super(message);
    }
}
