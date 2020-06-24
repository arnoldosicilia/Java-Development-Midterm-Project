package com.ironhack.midtermProject.controller.dto;

import com.ironhack.midtermProject.classes.Money;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateCreditCard {

    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    @NotNull
    private BigDecimal balance;
    @DecimalMax(value = "100000.00", message = "The credit limit can not be higher than 100000")
    @DecimalMin(value = "100.00", message = "The credit limit can not be lower than 100000")
    private BigDecimal creditLimit;
    @DecimalMax(value = "0.2", message = "The interest rate can not be higher than 0.2")
    @DecimalMin(value = "0.1", message = "The interest rate can not be lower than 0.1")
    private BigDecimal interestRate;

    public CreateCreditCard(Long primaryOwnerId, Long secondaryOwnerId, BigDecimal balance, BigDecimal creditLimit, BigDecimal interestRate) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Long getPrimaryOwnerId() {return primaryOwnerId;}
    public void setPrimaryOwnerId(Long primaryOwnerId) {this.primaryOwnerId = primaryOwnerId;}
    public Long getSecondaryOwnerId() {return secondaryOwnerId;}
    public void setSecondaryOwnerId(Long secondaryOwnerId) {this.secondaryOwnerId = secondaryOwnerId;}
    public BigDecimal getBalance() {return balance;}
    public void setBalance(BigDecimal balance) {this.balance = balance;}
    public BigDecimal getCreditLimit() {return creditLimit;}
    public void setCreditLimit(BigDecimal creditLimit) {this.creditLimit = creditLimit;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}

}
