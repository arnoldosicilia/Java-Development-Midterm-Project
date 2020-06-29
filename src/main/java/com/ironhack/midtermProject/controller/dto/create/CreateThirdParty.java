package com.ironhack.midtermProject.controller.dto.create;

public class CreateThirdParty {

    private String name;
    private String hashedKey;

    public CreateThirdParty(String name, String hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
    }

    public String getName() {
        return name;
    }

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
