package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.CreateSavings;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SavingsService {
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Savings create(CreateSavings createSavings){
        AccountHolder accountHolder1 = accountHolderRepository.findById(createSavings.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = accountHolderRepository.findById(createSavings.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));

        Savings savings = new Savings(accountHolder1,
                accountHolder2,
                new Money(new BigDecimal(createSavings.getBalance())),
                new Money(new BigDecimal(createSavings.getPenaltyFee())),
                createSavings.getSecretKey(),
                createSavings.getStatus(),
                createSavings.getMinimumBalance(),
                createSavings.getInterestRate());

        return savingsRepository.save(savings);
    }
}
