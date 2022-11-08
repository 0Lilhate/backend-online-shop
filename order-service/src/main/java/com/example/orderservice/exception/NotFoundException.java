package com.example.orderservice.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String messege){
        super(String.format("Not Found: %s", messege));
    }
}
