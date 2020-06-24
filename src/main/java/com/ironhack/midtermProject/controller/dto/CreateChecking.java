package com.ironhack.midtermProject.controller.dto;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.model.AccountHolder;

import java.math.BigDecimal;

public class CreateChecking {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String balance;
    private String penaltyFee;
    private String secretKey;
    private AccountStatus status;
    private String monthlyMaintenanceFee;
    private String minimumBalance;


    public CreateChecking(Long primaryOwnerId, Long secondaryOwnerId, String balance, String penaltyFee, String secretKey, AccountStatus status, String monthlyMaintenanceFee, String minimumBalance) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.secretKey = secretKey;
        this.status = status;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
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
    public String getMonthlyMaintenanceFee() {return monthlyMaintenanceFee;}
    public void setMonthlyMaintenanceFee(String monthlyMaintenanceFee) {this.monthlyMaintenanceFee = monthlyMaintenanceFee;}
    public String getMinimumBalance() {return minimumBalance;}
    public void setMinimumBalance(String minimumBalance) {this.minimumBalance = minimumBalance;}

}
