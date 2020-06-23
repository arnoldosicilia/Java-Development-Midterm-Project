package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.CreateCreditCard;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    public CreditCard create(CreateCreditCard createCreditCard) throws UserNotFoundException {

        System.out.println(createCreditCard.getPenaltyFee());
        AccountHolder accountHolder1 = accountHolderRepository.findById(createCreditCard.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("Not nd exceptionfou"));
        AccountHolder accountHolder2 = accountHolderRepository.findById(createCreditCard.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("no encontro el chisme"));
        Money balance = new Money(new BigDecimal(createCreditCard.getBalance()));
        Money penaltyFee = new Money(new BigDecimal(createCreditCard.getPenaltyFee()));
        Money creditLimit = new Money(new BigDecimal(createCreditCard.getCreditLimit()));

        CreditCard creditCard = new CreditCard(accountHolder1, accountHolder2, balance, penaltyFee, creditLimit, createCreditCard.getInterestRate());

        return creditCardRepository.save(creditCard);
    }
}
