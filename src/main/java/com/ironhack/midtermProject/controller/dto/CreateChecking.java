package com.ironhack.midtermProject.controller.dto;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.model.AccountHolder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateChecking {

    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private BigDecimal balance;
    private String secretKey;
    private AccountStatus status;

    public CreateChecking(@NotNull Long primaryOwnerId, Long secondaryOwnerId, BigDecimal balance, String secretKey, AccountStatus status) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.status = status;
    }

    public Long getPrimaryOwnerId() {return primaryOwnerId;}
    public void setPrimaryOwnerId(Long primaryOwnerId) {this.primaryOwnerId = primaryOwnerId;}
    public Long getSecondaryOwnerId() {return secondaryOwnerId;}
    public void setSecondaryOwnerId(Long secondaryOwnerId) {this.secondaryOwnerId = secondaryOwnerId;}
    public BigDecimal getBalance() {return balance;}
    public void setBalance(BigDecimal balance) {this.balance = balance;}
    public String getSecretKey() {return secretKey;}
    public void setSecretKey(String secretKey) {this.secretKey = secretKey;}
    public AccountStatus getStatus() {return status;}
    public void setStatus(AccountStatus status) {this.status = status;}
}
