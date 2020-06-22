package com.ironhack.midtermProject.model;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class StudentChecking extends Account {

    protected String secretKey;
    protected AccountStatus status;

    /** Constructors **/
    public StudentChecking() {}

    public StudentChecking(Long id, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Money penaltyFee, String secretKey, AccountStatus status) {
        super(id, primaryOwner, secondaryOwner, balance, penaltyFee);
        this.secretKey = secretKey;
        this.status = status;
    }

    /** Getters & Setters **/
    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public AccountStatus getStatus() {return status; }
    public void setStatus(AccountStatus status) {this.status = status;}
}