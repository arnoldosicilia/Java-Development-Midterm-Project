package com.ironhack.midtermProject.model;
import com.ironhack.midtermProject.classes.Money;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class CreditCard extends Account{

    private BigDecimal interestRate;

    /** Constructors **/
    public CreditCard() {}

    public CreditCard(Long id, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money balance, Money penaltyFee, BigDecimal interestRate) {
        super(id, primaryOwner, secondaryOwner, balance, penaltyFee);
        this.interestRate = interestRate;
    }

    /** Getters & Setters **/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

}
