package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateCreditCard;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.AuthenticationErrorException;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.*;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CreditCardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.ironhack.midtermProject.classes.Helpers.*;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    private final Logger LOGGER = LogManager.getLogger(CreditCardService.class);

    public CreditCard create(CreateCreditCard createCreditCard) throws UserNotFoundException {
        LOGGER.info("[INIT] - create(CreditCard)");
        AccountHolder accountHolder1 = accountHolderRepository.findById(createCreditCard.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = createCreditCard.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createCreditCard.getSecondaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found")) : null ;

        CreditCard creditCard = new CreditCard(accountHolder1,
                new Money(createCreditCard.getBalance()));

        if (accountHolder2 != null) {creditCard.setSecondaryOwner(accountHolder2);}
        creditCard.setCreditLimit(createCreditCard.getCreditLimit() != null ? new Money(createCreditCard.getCreditLimit()) : new Money(new BigDecimal("100")));
        creditCard.setInterestRate(createCreditCard.getInterestRate() != null ? createCreditCard.getInterestRate() : new BigDecimal("0.2"));

        LOGGER.info("[END] - create(CreditCard)");
        return creditCardRepository.save(creditCard);
    }

    public List<CreditCard> findAll(){ return creditCardRepository.findAll();}
    public CreditCard findById(Long id){return creditCardRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The account has not been found"));}

    public void updateInterest(Long id){
        LOGGER.info("[INIT] - updateInterest(CreditCard)");
        CreditCard account = creditCardRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        if(calculateMonths(account.getLastInterestUpdate()) >= 1) {
            LOGGER.info("Need to update the interest");
            BigDecimal calculatedInterest = account.getBalance().getAmount().multiply(account.getInterestRate().divide(new BigDecimal("12"), 2, RoundingMode.HALF_EVEN)).multiply(new BigDecimal(calculateMonths(account.getLastInterestUpdate())));
            account.getBalance().increaseAmount(calculatedInterest);
            account.setLastInterestUpdate(account.getLastInterestUpdate().plusMonths(calculateMonths(account.getLastInterestUpdate())));
        }
        LOGGER.info("[END] - updateInterest(CreditCard)");
        creditCardRepository.save(account);
    }

    public ShowBalance checkBalance(Long id, Authentication authentication){
        LOGGER.info("[INIT] - checkBalance(CreditCard)");
        AccountHolder loggedUser = accountHolderRepository.findByUsername(authentication.getName());
        boolean isAdmin = loggedUser.getRoles().stream().anyMatch(x-> x.getRole().equals(SystemRole.ADMIN));
        LOGGER.info("Logged user role is " + loggedUser.getRoles());

        CreditCard account = creditCardRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        if(!isAdmin || !loggedUser.accessAllAccounts().contains(account)){
            LOGGER.error("The logged user is not an admin or owner of the account");
            throw new AuthenticationErrorException("The user is not the owner nor an admin");
        }
        updateInterest(id);
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());
        LOGGER.info("[END] - checkBalance(CreditCard)");
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - debitBalance(CreditCard)");
        CreditCard account = creditCardRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        updateInterest(id);
        account.getBalance().decreaseAmount(amount);
        creditCardRepository.save(account);
        LOGGER.info("[END] - debitBalance(CreditCard)");
    }

    public void creditBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - creditBalance(CreditCard)");
        CreditCard account = creditCardRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        updateInterest(id);
        account.getBalance().increaseAmount(amount);
        creditCardRepository.save(account);
        LOGGER.info("[END] - creditBalance(CreditCard)");
    }
}
