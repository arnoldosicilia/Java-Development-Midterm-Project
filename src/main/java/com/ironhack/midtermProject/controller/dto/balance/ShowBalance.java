package com.ironhack.midtermProject.controller.dto.balance;

import java.math.BigDecimal;
import java.util.Currency;

public class ShowBalance {

    private Long accountId;
    private BigDecimal balanceAmount;
    private Currency balanceCurrency;

    public ShowBalance(Long accountId, BigDecimal balanceAmount, Currency balanceCurrency) {
        this.accountId = accountId;
        this.balanceAmount = balanceAmount;
        this.balanceCurrency = balanceCurrency;
    }

    public Long getAccountId() {return accountId;}
    public void setAccountId(Long accountId) {this.accountId = accountId;}
    public BigDecimal getBalanceAmount() {return balanceAmount;}
    public void setBalanceAmount(BigDecimal balanceAmount) {this.balanceAmount = balanceAmount;}
    public Currency getBalanceCurrency() {return balanceCurrency;}
    public void setBalanceCurrency(Currency balanceCurrency) {this.balanceCurrency = balanceCurrency;}
}
