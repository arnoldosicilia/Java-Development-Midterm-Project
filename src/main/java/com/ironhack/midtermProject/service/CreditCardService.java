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
        AccountHolder accountHolder2 = createCreditCard.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createCreditCard.getSecondaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found")) : null ;

        CreditCard creditCard = new CreditCard(accountHolder1,
                new Money(new BigDecimal(createCreditCard.getBalance())),
                new Money(new BigDecimal(createCreditCard.getPenaltyFee())));

        if (accountHolder2 != null) {creditCard.setSecondaryOwner(accountHolder2);}
        creditCard.setCreditLimit(createCreditCard.getCreditLimit() != null ? new Money(new BigDecimal(createCreditCard.getCreditLimit())) : new Money(new BigDecimal("100")));
        creditCard.setInterestRate(createCreditCard.getInterestRate() != null ? createCreditCard.getInterestRate() : new BigDecimal("0.2"));

        return creditCardRepository.save(creditCard);
    }
}
