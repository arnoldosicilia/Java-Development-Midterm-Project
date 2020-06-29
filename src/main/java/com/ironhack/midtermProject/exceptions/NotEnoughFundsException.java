package com.ironhack.midtermProject.exceptions;

public class NotEnoughFundsException extends Exception{
    public NotEnoughFundsException(){
        super();
    }
    public NotEnoughFundsException(String message){
        super(message);
    }
}
