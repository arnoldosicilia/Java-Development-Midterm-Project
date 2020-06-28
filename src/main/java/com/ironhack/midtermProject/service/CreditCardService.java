package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateCreditCard;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
                new Money(createCreditCard.getBalance()));

        if (accountHolder2 != null) {creditCard.setSecondaryOwner(accountHolder2);}
        creditCard.setCreditLimit(createCreditCard.getCreditLimit() != null ? new Money(createCreditCard.getCreditLimit()) : new Money(new BigDecimal("100")));
        creditCard.setInterestRate(createCreditCard.getInterestRate() != null ? createCreditCard.getInterestRate() : new BigDecimal("0.2"));

        return creditCardRepository.save(creditCard);
    }

    public List<CreditCard> findAll(){ return creditCardRepository.findAll();}
    public CreditCard findById(Long id){return creditCardRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The account has not been found"));}

    public ShowBalance checkBalance(Long id){
        CreditCard account = creditCardRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        CreditCard account = creditCardRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().decreaseAmount(amount);
        creditCardRepository.save(account);
    }

    public void creditBalance(Long id, BigDecimal amount) {
        CreditCard account = creditCardRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().increaseAmount(amount);
        creditCardRepository.save(account);
    }
}
