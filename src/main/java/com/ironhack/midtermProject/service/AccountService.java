package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.AuthenticationErrorException;
import com.ironhack.midtermProject.exceptions.FraudException;
import com.ironhack.midtermProject.exceptions.NotEnoughFundsException;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.AccountRepository;
import com.ironhack.midtermProject.repository.TransferenceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    @Autowired
    FraudService fraudService;

    private final Logger LOGGER = LogManager.getLogger(AccountService.class);

    @Transactional
    public Transference transfer(NewTransference newTransference, Authentication authentication) throws AuthenticationErrorException, FraudException, NotEnoughFundsException {
        LOGGER.info("[INIT] - transfer(Account)");
        AccountHolder loggedUser = accountHolderRepository.findByUsername(authentication.getName());
        Account originAccount = accountRepository.findById(newTransference.getOriginId()).orElseThrow(()-> new AccountNotFoundException("Not found the origin account"));

        if(!loggedUser.accessAllAccounts().contains(originAccount)){
            LOGGER.error("The account does not belong to the logged in user");
            throw new AuthenticationErrorException("The Account does not belong to the logged user");
        }
        if(!fraudService.firstCondition(newTransference) || !fraudService.secondCondition(newTransference)) {
            LOGGER.error("The account has met some fraud detection conditions");
            throw new FraudException("This transaction can not be done due to fraud detections"); }
        if(newTransference.getAmount().compareTo(originAccount.getBalance().getAmount()) > 0 ){
            LOGGER.error("The account has not enough funds");
            throw new NotEnoughFundsException("There are not enough funds in the account");
        }

        Account destinationAccount = accountRepository.findById(newTransference.getDestinationId()).orElseThrow(() -> new AccountNotFoundException("Not found the destination account"));
        Money amount = new Money(newTransference.getAmount());

        originAccount.getBalance().decreaseAmount(amount);
        destinationAccount.getBalance().increaseAmount(amount);
        Transference transference = new Transference(originAccount, destinationAccount, amount);

        LOGGER.info("[END] - transfer(Account)");
        return transferenceRepository.save(transference);
    }
}
