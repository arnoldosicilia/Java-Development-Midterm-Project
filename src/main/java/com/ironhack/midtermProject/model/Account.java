package com.ironhack.midtermProject.model;

import com.ironhack.midtermProject.classes.Money;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @ManyToOne(optional = false)
    protected AccountHolder primaryOwner;
    @ManyToOne(optional = true)
    protected AccountHolder secondaryOwner;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    protected Money balance;
    @Embedded
    @AttributeOverrides(value ={
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_fee_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    protected Money penaltyFee;


    /** Constructors **/
    public Account() {}

    public Account(AccountHolder primaryOwner, Money balance) {
        this.primaryOwner = primaryOwner;
        this.balance = balance;
        this.penaltyFee = new Money(new BigDecimal("40"));
    }

    /** Getters & Setters **/
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public AccountHolder getPrimaryOwner() {return primaryOwner;}
    public void setPrimaryOwner(AccountHolder primaryOwner) {this.primaryOwner = primaryOwner;}
    public Money getBalance() {return balance;}
    public void setBalance(Money balance) {this.balance = balance;}
    public Money getPenaltyFee() {return penaltyFee;}
    public void setPenaltyFee(Money penaltyFee) {this.penaltyFee = penaltyFee;}
    public AccountHolder getSecondaryOwner() {return secondaryOwner;}
    public void setSecondaryOwner(AccountHolder secondaryOwner) {this.secondaryOwner = secondaryOwner;}

    /** Methods **/
    public void applyPenaltyFee(){
        this.balance.decreaseAmount(this.penaltyFee.getAmount());
    }


}
