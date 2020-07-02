package com.ironhack.midtermProject.model;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Savings extends Account{

    private String secretKey;
    private AccountStatus status;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;
    private BigDecimal interestRate;
    private boolean belowMinimumBalance;
    private LocalDate lastInterestUpdate;

    /** Constructors **/
    public Savings() {}

    public Savings(AccountHolder primaryOwner, Money balance, String secretKey, AccountStatus status) {
        super(primaryOwner, balance);
        this.secretKey = secretKey;
        this.status = status;
        this.belowMinimumBalance =  false;
    }

    /** Getters & Setters **/
    public Money getMinimumBalance() {return minimumBalance;}
    public void setMinimumBalance(Money minimumBalance) {this.minimumBalance = minimumBalance;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}
    public boolean isBelowMinimumBalance() {return belowMinimumBalance; }
    public void setBelowMinimumBalance(boolean belowMinimumBalance) {this.belowMinimumBalance = belowMinimumBalance;}
    public String getSecretKey() {return secretKey;}
    public void setSecretKey(String secretKey) {this.secretKey = secretKey;}
    public AccountStatus getStatus() {return status;}
    public void setStatus(AccountStatus status) {this.status = status;}
    public LocalDate getLastInterestUpdate() {return lastInterestUpdate;}
    public void setLastInterestUpdate(LocalDate lastInterestUpdate) {this.lastInterestUpdate = lastInterestUpdate;}

}