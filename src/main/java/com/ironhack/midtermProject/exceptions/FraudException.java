package com.ironhack.midtermProject.exceptions;

public class FraudException extends Exception{
    public FraudException(){
        super();
    }
    public FraudException(String message){
        super(message);
    }
}