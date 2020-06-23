package com.ironhack.midtermProject.controller.dto;
import com.ironhack.midtermProject.enums.AccountStatus;

public class CreateStudentChecking {

    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String balance;
    private String penaltyFee;
    private String secretKey;
    private AccountStatus status;

    public CreateStudentChecking(Long primaryOwnerId, Long secondaryOwnerId, String balance, String penaltyFee, String secretKey, AccountStatus status) {
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.secretKey = secretKey;
        this.status = status;
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
}
