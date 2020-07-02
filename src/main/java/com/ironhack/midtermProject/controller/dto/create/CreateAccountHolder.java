package com.ironhack.midtermProject.controller.dto.create;

import java.time.LocalDate;

public class CreateAccountHolder {

    private String name;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private String primaryAddressDirection;
    private Integer primaryAddressNumber;
    private String mailingAddressDirection;
    private Integer mailingAddressNumber;

    /** Constructors **/
    public CreateAccountHolder(String name, String username, String password, LocalDate dateOfBirth, String primaryAddressDirection, Integer primaryAddressNumber, String mailingAddressDirection, Integer mailingAddressNumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddressDirection = primaryAddressDirection;
        this.primaryAddressNumber = primaryAddressNumber;
        this.mailingAddressDirection = mailingAddressDirection;
        this.mailingAddressNumber = mailingAddressNumber;
    }


    /** Getters & Setters **/
    public String getName(){return name;}
    public void setName(String name) {this.name = name;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}
    public String getPrimaryAddressDirection() {return primaryAddressDirection;}
    public void setPrimaryAddressDirection(String primaryAddressDirection) {this.primaryAddressDirection = primaryAddressDirection;}
    public Integer getPrimaryAddressNumber() {return primaryAddressNumber;}
    public void setPrimaryAddressNumber(Integer primaryAddressNumber) {this.primaryAddressNumber = primaryAddressNumber;}
    public String getMailingAddressDirection() {return mailingAddressDirection;}
    public void setMailingAddressDirection(String mailingAddressDirection) {this.mailingAddressDirection = mailingAddressDirection;}
    public Integer getMailingAddressNumber() {return mailingAddressNumber;}
    public void setMailingAddressNumber(Integer mailingAddressNumber) {this.mailingAddressNumber = mailingAddressNumber;}
}
