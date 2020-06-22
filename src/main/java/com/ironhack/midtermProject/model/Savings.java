package com.ironhack.midtermProject.model;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.enums.AccountStatus;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Savings extends StudentChecking{

    private BigDecimal minimumBalance;
    private BigDecimal interestRate;

    /** Constructors **/
    public Savings() {}

    public Savings(Long id, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Money penaltyFee, String secretKey, AccountStatus status, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(id, primaryOwner, secondaryOwner, balance, penaltyFee, secretKey, status);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    /** Getters & Setters **/
    public BigDecimal getMinimumBalance() {return minimumBalance;}
    public void setMinimumBalance(BigDecimal minimumBalance) {this.minimumBalance = minimumBalance;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}

}