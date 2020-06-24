package com.ironhack.midtermProject.classes;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String direction;
    private int number;

    /** Constructors **/
    public Address(){}

    public Address(String direction, int number) {
        this.direction = direction;
        this.number = number;
    }

    /** Getters & Setters **/
    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction = direction;}
    public int getNumber() {return number;}
    public void setNumber(int number) {this.number = number;}

}


