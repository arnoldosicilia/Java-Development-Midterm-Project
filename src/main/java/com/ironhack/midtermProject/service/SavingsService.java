package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateSavings;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SavingsService {
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Savings create(CreateSavings createSavings){
        AccountHolder accountHolder1 = accountHolderRepository.findById(createSavings.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = createSavings.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createSavings.getSecondaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found")) : null ;

        Savings savings = new Savings(accountHolder1,
                new Money(createSavings.getBalance()),
                createSavings.getSecretKey(),
                createSavings.getStatus());

        if (accountHolder2 != null) {savings.setSecondaryOwner(accountHolder2);}

        savings.setInterestRate(createSavings.getInterestRate() != null ? createSavings.getInterestRate() : new BigDecimal("0.0025"));
        savings.setMinimumBalance(createSavings.getMinimumBalance() != null ? new Money(createSavings.getMinimumBalance()) : new Money(new BigDecimal("1000")));


        return savingsRepository.save(savings);
    }

    public List<Savings> findAll(){ return savingsRepository.findAll();}
    public Savings findById(Long id){return savingsRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The account has not been found"));}

    public ShowBalance checkBalance(Long id){
        Savings account = savingsRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        Savings account = savingsRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().decreaseAmount(amount);
        savingsRepository.save(account);
    }

    public void creditBalance(Long id, BigDecimal amount) {
        Savings account = savingsRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().increaseAmount(amount);
        savingsRepository.save(account);
    }

}
