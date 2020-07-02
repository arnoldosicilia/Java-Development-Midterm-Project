package com.ironhack.midtermProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.midtermProject.classes.Address;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class AccountHolder extends User {

    private LocalDate dateOfBirth;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "direction", column = @Column(name = "primary_address_direction")),
            @AttributeOverride(name = "number", column = @Column(name = "primary_address_number"))
    })
    @NotNull
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "direction", column = @Column(name = "mailing_address_direction")),
            @AttributeOverride(name = "number", column = @Column(name = "mailing_address_number"))
    })
    private Address mailingAddress;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccounts;
    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccounts;


    /** Constructors **/
    public AccountHolder() {}

    public AccountHolder(String name, String username, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.primaryAccounts = new ArrayList<>();
        this.secondaryAccounts = new ArrayList<>();
    }

    /** Getters & Setters **/
    public LocalDate getDateOfBirth() { return dateOfBirth;}
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth;}
    public Address getPrimaryAddress() { return primaryAddress;}
    public void setPrimaryAddress(Address primaryAddress) {this.primaryAddress = primaryAddress;}
    public Address getMailingAddress() {return mailingAddress;}
    public void setMailingAddress(Address mailingAddress) {this.mailingAddress = mailingAddress;}
    public List<Account> getPrimaryAccounts() {return primaryAccounts;}
    public void setPrimaryAccounts(List<Account> primaryAccounts) {this.primaryAccounts = primaryAccounts;}
    public List<Account> getSecondaryAccounts() {return secondaryAccounts;}
    public void setSecondaryAccounts(List<Account> secondaryAccounts) {this.secondaryAccounts = secondaryAccounts;}

    public List<Account> accessAllAccounts(){
        List<Account> result = new ArrayList<>();
        this.primaryAccounts.stream().map(x->result.add(x)).collect(Collectors.toList());
        this.secondaryAccounts.stream().map(x->result.add(x)).collect(Collectors.toList());
        return result;
    }
}
