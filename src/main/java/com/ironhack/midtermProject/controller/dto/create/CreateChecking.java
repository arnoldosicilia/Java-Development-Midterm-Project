package com.ironhack.midtermProject.controller.dto.create;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.model.AccountHolder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreateChecking {

    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    @NotNull
    private BigDecimal balance;
    @NotNull
    private String secretKey;
    @NotNull
    private AccountStatus status;

    /** Constructors **/
    public CreateChecking(@NotNull Long primaryOwnerId, Long secondaryOwnerId, @NotNull BigDecimal balance, @NotNull String secretKey, @NotNull AccountStatus status) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.secretKey = secretKey;
        this.status = status;
    }

    /** Getters & Setters **/
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
