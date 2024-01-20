package org.example.exceptions;

public class DepthNotFoundException extends RuntimeException{
    public DepthNotFoundException(String message){
        super(message);
    }
}
