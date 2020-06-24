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

    public CreditCard(AccountHolder primaryOwner, Money balance, Money penaltyFee) {
        super(primaryOwner, balance, penaltyFee);
    }

    /** Getters & Setters **/
    public Money getCreditLimit() {return creditLimit;}
    public void setCreditLimit(Money creditLimit) {this.creditLimit = creditLimit;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}
}
