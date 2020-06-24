package com.ironhack.midtermProject.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User {

    /** Constructors **/
    public Admin() {}

    public Admin(String name, String username, String password) {
        super(name, username, password);
    }
    /** Getters & Setters **/

}