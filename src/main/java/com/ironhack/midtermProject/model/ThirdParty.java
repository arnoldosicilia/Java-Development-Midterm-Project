package com.ironhack.midtermProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ThirdParty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String hashKey;

    /** Constructors **/
    public ThirdParty() {}

    public ThirdParty(@NotNull String name, @NotNull String hashKey) {
        this.name = name;
        this.hashKey = hashKey;
    }


    /** Getters & Setters **/
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getHashKey() {return hashKey;}
    public void setHashKey(String hashKey) {this.hashKey = hashKey;}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
}