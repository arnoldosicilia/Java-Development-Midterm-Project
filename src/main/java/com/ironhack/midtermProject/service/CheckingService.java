package com.ironhack.midtermProject.service;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.CreateChecking;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Checking create(CreateChecking createChecking) throws UserNotFoundException{
        AccountHolder accountHolder1 = accountHolderRepository.findById(createChecking.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = accountHolderRepository.findById(createChecking.getSecondaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));

        Checking checking = new Checking(accountHolder1,
                accountHolder2,
                new Money(new BigDecimal(createChecking.getBalance())),
                new Money(new BigDecimal(createChecking.getPenaltyFee())),
                createChecking.getSecretKey(),
                createChecking.getStatus(),
                createChecking.getMonthlyMaintenanceFee(),
                new Money(new BigDecimal(createChecking.getMinimumBalance())));

        return checkingRepository.save(checking);
    }
}
