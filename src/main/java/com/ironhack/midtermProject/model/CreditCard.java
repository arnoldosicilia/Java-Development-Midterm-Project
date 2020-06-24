package com.ironhack.midtermProject.model;
import com.ironhack.midtermProject.classes.Money;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class CreditCard extends Account{

    private Money creditLimit;
    private BigDecimal interestRate;

    /** Constructors **/
    public CreditCard() {}

    public CreditCard(AccountHolder primaryOwner, Money balance, Money penaltyFee, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, balance, penaltyFee);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    /** Getters & Setters **/
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    public Money getCreditLimit() {return creditLimit;}
    public void setCreditLimit(Money creditLimit) {this.creditLimit = creditLimit;}
}
