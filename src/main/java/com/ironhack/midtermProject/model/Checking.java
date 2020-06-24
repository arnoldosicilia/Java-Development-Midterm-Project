package com.ironhack.midtermProject.model;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Checking extends StudentChecking {

    private BigDecimal monthlyMaintenanceFee;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency"))
    })
    private Money minimumBalance;

    /** Constructors **/
    public Checking() {}

    public Checking(AccountHolder primaryOwner, Money balance, Money penaltyFee, String secretKey, AccountStatus status, BigDecimal monthlyMaintenanceFee, Money minimumBalance) {
        super(primaryOwner, balance, penaltyFee, secretKey, status);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
    }

    /** Getters & Setters **/
    public Money getMinimumBalance() {
        return minimumBalance;
    }
    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }
    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {this.monthlyMaintenanceFee = monthlyMaintenanceFee;}

}
