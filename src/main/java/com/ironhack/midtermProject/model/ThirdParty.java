package com.ironhack.midtermProject.model;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class ThirdParty extends User{

    private String hashKey;

    /** Constructors **/
    public ThirdParty() {}

    public ThirdParty(String name, String username, String password, String hashKey) {
        super(name, username, password);
        this.hashKey = hashKey;
    }

    /** Getters & Setters **/
    public String getHashKey() {return hashKey;}
    public void setHashKey(String hashKey) {this.hashKey = hashKey;}

}