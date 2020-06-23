package com.ironhack.midtermProject.controller.dto;

import com.ironhack.midtermProject.enums.AccountStatus;

import java.math.BigDecimal;

public class CreateSavings {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String balance;
    private String penaltyFee;
    private String secretKey;
    private AccountStatus status;
    private BigDecimal minimumBalance;
    private BigDecimal interestRate;

    public CreateSavings(Long primaryOwnerId, Long secondaryOwnerId, String balance, String penaltyFee, String secretKey, AccountStatus status, BigDecimal minimumBalance, BigDecimal interestRate) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.secretKey = secretKey;
        this.status = status;
        this.minimumBalance = minimumBalance;
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
    public String getSecretKey() {return secretKey;}
    public void setSecretKey(String secretKey) {this.secretKey = secretKey;}
    public AccountStatus getStatus() {return status;}
    public void setStatus(AccountStatus status) {this.status = status;}
    public BigDecimal getMinimumBalance() {return minimumBalance;}
    public void setMinimumBalance(BigDecimal minimumBalance) {this.minimumBalance = minimumBalance;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}
}
