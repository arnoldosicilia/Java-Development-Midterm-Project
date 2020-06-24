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

    private Money minimumBalance;
    private BigDecimal interestRate;

    /** Constructors **/
    public Savings() {}

    public Savings(AccountHolder primaryOwner, Money balance, Money penaltyFee, String secretKey, AccountStatus status) {
        super(primaryOwner, balance, penaltyFee, secretKey, status);
    }



    /** Getters & Setters **/
    public Money getMinimumBalance() {return minimumBalance;}
    public void setMinimumBalance(Money minimumBalance) {this.minimumBalance = minimumBalance;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}

}