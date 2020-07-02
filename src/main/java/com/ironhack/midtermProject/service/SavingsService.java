package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateSavings;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.AuthenticationErrorException;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.SavingsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static com.ironhack.midtermProject.classes.Helpers.*;

@Service
public class SavingsService {
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    private final Logger LOGGER = LogManager.getLogger(SavingsService.class);

    public Savings create(CreateSavings createSavings){
        LOGGER.info("[INIT] - create(Savings)");
        AccountHolder accountHolder1 = accountHolderRepository.findById(createSavings.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = createSavings.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createSavings.getSecondaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found")) : null ;

        Savings savings = new Savings(accountHolder1,
                new Money(createSavings.getBalance()),
                createSavings.getSecretKey(),
                createSavings.getStatus());

        if (accountHolder2 != null) {savings.setSecondaryOwner(accountHolder2);}

        savings.setInterestRate(createSavings.getInterestRate() != null ? createSavings.getInterestRate() : new BigDecimal("0.0025"));
        savings.setMinimumBalance(createSavings.getMinimumBalance() != null ? new Money(createSavings.getMinimumBalance()) : new Money(new BigDecimal("1000")));
        savings.setLastInterestUpdate(LocalDate.now());

        LOGGER.info("[END] - create(Savings)");
        return savingsRepository.save(savings);
    }

    public List<Savings> findAll(){ return savingsRepository.findAll();}
    public Savings findById(Long id){return savingsRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The account has not been found"));}

    public void updateInterest(Long id){
        LOGGER.info("[INIT] - updateInterest(Savings)");
        Savings account = savingsRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));

        if(calculateYears(account.getLastInterestUpdate()) >= 1) {
            LOGGER.info("Need to update the interest");
            BigDecimal calculatedInterest = account.getBalance().getAmount().multiply(account.getInterestRate()).multiply(new BigDecimal(calculateYears(account.getLastInterestUpdate())));
            account.getBalance().increaseAmount(calculatedInterest);
            account.setLastInterestUpdate(account.getLastInterestUpdate().plusYears(calculateYears(account.getLastInterestUpdate())));
        }
        if(account.isBelowMinimumBalance() && account.getBalance().getAmount().compareTo(account.getMinimumBalance().getAmount()) > 0){
            LOGGER.info("The balance has increased above the minimum balance -> " + account.getBalance().getAmount());
            account.setBelowMinimumBalance(false);
        }
        savingsRepository.save(account);
        LOGGER.info("[END] - updateInterest(Savings)");
    }

    public ShowBalance checkBalance(Long id, Authentication authentication){
        LOGGER.info("[INIT] - checkBalance(Savings)");
        AccountHolder loggedUser = accountHolderRepository.findByUsername(authentication.getName());
        boolean isAdmin = loggedUser.getRoles().stream().anyMatch(x-> x.getRole().equals(SystemRole.ADMIN));
        LOGGER.info("Logged user role is " + loggedUser.getRoles());
        Savings account = savingsRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());

        if(!isAdmin || !loggedUser.accessAllAccounts().contains(account)){
            LOGGER.error("The logged user is not an admin or owner of the account");
            throw new AuthenticationErrorException("The user is not the owner nor an admin");
        }
        updateInterest(id);
        if(account.getMinimumBalance().getAmount().compareTo(account.getBalance().getAmount()) > 0 && !account.isBelowMinimumBalance()){
            LOGGER.error("The balance is below the minimum balance so a penalty fee is applied");
            account.getBalance().decreaseAmount(account.getPenaltyFee());
            savingsRepository.save(account);
        }

        LOGGER.info("[END] - checkBalance(Savings)");
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - debitBalance(Savings)");
        Savings account = savingsRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        updateInterest(id);
        account.getBalance().decreaseAmount(amount);
        if(account.getMinimumBalance().getAmount().compareTo(account.getBalance().getAmount()) > 0 && !account.isBelowMinimumBalance()){
            LOGGER.error("The balance is below the minimum balance so a penalty fee is applied");
            account.getBalance().decreaseAmount(account.getPenaltyFee());
        }
        savingsRepository.save(account);
        LOGGER.info("[END] - debitBalance(Savings)");
    }

    public void creditBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - creditBalance(Savings)");
        Savings account = savingsRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        updateInterest(id);
        account.getBalance().increaseAmount(amount);

        if(account.isBelowMinimumBalance() && account.getBalance().getAmount().compareTo(account.getMinimumBalance().getAmount()) > 0){
            LOGGER.info("The balance has increased above the minimum balance -> " + account.getBalance().getAmount());
            account.setBelowMinimumBalance(false);
        }
        savingsRepository.save(account);
        LOGGER.info("[END] - creditBalance(Savings)");
    }

}
