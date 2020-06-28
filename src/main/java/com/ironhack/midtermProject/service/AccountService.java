package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.AuthenticationErrorException;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.AccountRepository;
import com.ironhack.midtermProject.repository.TransferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransferenceRepository transferenceRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Transactional
    public Transference transfer(NewTransference newTransference, Authentication authentication) throws AuthenticationErrorException {

        AccountHolder loggedUser = accountHolderRepository.findByUsername(authentication.getName());
        Account originAccount = accountRepository.findById(newTransference.getOriginId()).orElseThrow(()-> new AccountNotFoundException("Not found the origin account"));

        if(!loggedUser.getAllAccounts().contains(originAccount)){
            throw new AuthenticationErrorException("The Account does not belong to the logged user");
        }
        Account destinationAccount = accountRepository.findById(newTransference.getDestinationId()).orElseThrow(()-> new AccountNotFoundException("Not found the destination account"));
        Money amount = new Money(newTransference.getAmount());

        originAccount.getBalance().decreaseAmount(amount);
        destinationAccount.getBalance().increaseAmount(amount);
        Transference transference = new Transference(originAccount, destinationAccount, amount);

        return transferenceRepository.save(transference);
    }




}
