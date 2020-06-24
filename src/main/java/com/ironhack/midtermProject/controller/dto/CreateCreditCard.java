package com.ironhack.midtermProject.controller.dto;

import com.ironhack.midtermProject.classes.Money;

import java.math.BigDecimal;

public class CreateCreditCard {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String balance;
    private String penaltyFee;
    private String creditLimit;
    private BigDecimal interestRate;

    public CreateCreditCard(Long primaryOwnerId, Long secondaryOwnerId, String balance, String penaltyFee, String creditLimit, BigDecimal interestRate) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Long getPrimaryOwnerId() {return primaryOwnerId;}
    public void setPrimaryOwnerId(Long primaryOwnerId) {this.primaryOwnerId = primaryOwnerId;}
    public Long getSecondaryOwnerId() {return secondaryOwnerId;}
    public void setSecondaryOwnerId(Long secondaryOwnerId) {this.secondaryOwnerId = secondaryOwnerId;}
    public String getBalance() {return balance;}
    public void setBalance(String balance) {this.balance = balance;}
    public String getPenaltyFee() {return penaltyFee;}
    public void setPenaltyFee(String penaltyFee) {this.penaltyFee = penaltyFee;}
    public String getCreditLimit() {return creditLimit;}
    public void setCreditLimit(String creditLimit) {this.creditLimit = creditLimit;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}

}
