package com.ironhack.midtermProject.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {

    @Id
    @Column(unique = true)
    private String name;

    /** Constructors **/
    public Admin() {}

    public Admin(String name) {
        this.name = name;
    }

    /** Getters & Setters **/
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

}