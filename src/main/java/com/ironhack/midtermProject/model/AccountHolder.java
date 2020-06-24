package com.ironhack.midtermProject.model;

import com.ironhack.midtermProject.classes.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "direction", column = @Column(name = "primary_address_direction")),
            @AttributeOverride(name = "number", column = @Column(name = "primary_address_number"))
    })
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "direction", column = @Column(name = "mailing_address_direction")),
            @AttributeOverride(name = "number", column = @Column(name = "mailing_address_number"))
    })
    private Address mailingAddress;
    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryAccounts;
    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryAccounts;


    /** Constructors **/
    public AccountHolder() {}

    public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress, List<Account> primaryAccounts, List<Account> secondaryAccounts) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.primaryAccounts = primaryAccounts;
        this.secondaryAccounts = secondaryAccounts;
    }

    /** Getters & Setters **/
    public Long getId() {return id;}
    public void setId(Long id) { this.id = id;}
    public String getName() { return name;}
    public void setName(String name) { this.name = name;}
    public LocalDate getDateOfBirth() { return dateOfBirth;}
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth;}
    public Address getPrimaryAddress() { return primaryAddress;}
    public void setPrimaryAddress(Address primaryAddress) {this.primaryAddress = primaryAddress;}
    public Address getMailingAddress() {return mailingAddress;}
    public void setMailingAddress(Address mailingAddress) {this.mailingAddress = mailingAddress;}

}
