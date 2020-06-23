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
        AccountHolder accountHolder1 = accountHolderRepository.findById(createCreditCard.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = accountHolderRepository.findById(createCreditCard.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));

        CreditCard creditCard = new CreditCard(accountHolder1,
                accountHolder2,
                new Money(new BigDecimal(createCreditCard.getBalance())),
                new Money(new BigDecimal(createCreditCard.getPenaltyFee())),
                new Money(new BigDecimal(createCreditCard.getCreditLimit())),
                createCreditCard.getInterestRate());

        return creditCardRepository.save(creditCard);
    }
}
