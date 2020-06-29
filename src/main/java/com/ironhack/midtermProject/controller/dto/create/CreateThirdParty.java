package com.ironhack.midtermProject.controller.dto.create;

import javax.validation.constraints.NotNull;

public class CreateThirdParty {

    @NotNull
    private String name;
    @NotNull
    private String hashedKey;

    /** Constructors **/
    public CreateThirdParty() {}

    public CreateThirdParty(String name, String hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
    }

    /** Getters & Setters **/
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedKey() {
        return hashedKey;
    }
    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
