package com.ironhack.midtermProject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThirdParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String hashKey;


    /** Constructors **/
    public ThirdParty() {}

    public ThirdParty(String name, String hashKey) {
        this.name = name;
        this.hashKey = hashKey;
    }

    /** Getters & Setters **/
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getHashKey() {return hashKey;}
    public void setHashKey(String hashKey) {this.hashKey = hashKey;}

}