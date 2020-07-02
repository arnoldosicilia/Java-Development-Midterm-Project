package com.ironhack.midtermProject.exceptions;

public class AuthenticationErrorException extends RuntimeException{
    public AuthenticationErrorException() {}
    public AuthenticationErrorException(String message) {super(message);}
}