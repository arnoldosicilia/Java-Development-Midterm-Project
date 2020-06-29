package com.ironhack.midtermProject.model;
import com.ironhack.midtermProject.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class CreditCard extends Account{
    @NotNull
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency"))
    })
    private Money creditLimit;
    @NotNull
    @DecimalMax(value = "0.2")
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;
    private LocalDate lastInterestUpdate;

    /** Constructors **/
    public CreditCard() {}

    public CreditCard(AccountHolder primaryOwner, Money balance) {super(primaryOwner, balance);}

    /** Getters & Setters **/
    public Money getCreditLimit() {return creditLimit;}
    public void setCreditLimit(Money creditLimit) {this.creditLimit = creditLimit;}
    public BigDecimal getInterestRate() {return interestRate;}
    public void setInterestRate(BigDecimal interestRate) {this.interestRate = interestRate;}
    public LocalDate getLastInterestUpdate() {return lastInterestUpdate; }
    public void setLastInterestUpdate(LocalDate lastInterestUpdate) {this.lastInterestUpdate = lastInterestUpdate; }


}
