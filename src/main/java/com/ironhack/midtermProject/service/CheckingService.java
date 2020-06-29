package com.ironhack.midtermProject.service;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateChecking;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.AuthenticationErrorException;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.*;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import com.ironhack.midtermProject.repository.StudentCheckingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.ironhack.midtermProject.classes.Helpers.calculateYears;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    private final Logger LOGGER = LogManager.getLogger(CheckingService.class);


    public StudentChecking create(CreateChecking createChecking) throws UserNotFoundException {
        LOGGER.info("[INIT] - create(Checking)");
        AccountHolder accountHolder1 = accountHolderRepository.findById(createChecking.getPrimaryOwnerId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = createChecking.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createChecking.getSecondaryOwnerId()).orElseThrow(() -> new UserNotFoundException("User not found")) : null;

        if (calculateYears(accountHolder1.getDateOfBirth()) < 24) {
            LOGGER.info("The AccountHolder is less than 24 so a StudentChecking is created");
            StudentChecking studentChecking = new StudentChecking(accountHolder1,
                    new Money(createChecking.getBalance()),
                    createChecking.getSecretKey(),
                    createChecking.getStatus());

            if (accountHolder2 != null) {studentChecking.setSecondaryOwner(accountHolder2);}

            return studentCheckingRepository.save(studentChecking);

        } else {
             Checking checking = new Checking(accountHolder1,
                    new Money(createChecking.getBalance()),
                    createChecking.getSecretKey(),
                    createChecking.getStatus());

            if (accountHolder2 != null) {checking.setSecondaryOwner(accountHolder2);}

            checking.setMonthlyMaintenanceFee(new Money(new BigDecimal("250")));
            checking.setMinimumBalance(new Money(new BigDecimal("12")));
            LOGGER.info("[END] - create(Checking)");
            return checkingRepository.save(checking);
        }
    }

    public List<Checking> findAll(){ return checkingRepository.findAll();}
    public Checking findById(Long id) {return checkingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));}


    public ShowBalance checkBalance(Long id,  Authentication authentication) throws AuthenticationErrorException {
        LOGGER.info("[INIT] - checkBalance(Checking)");
        AccountHolder loggedUser = accountHolderRepository.findByUsername(authentication.getName());
        boolean isAdmin = loggedUser.getRoles().stream().anyMatch(x-> x.getRole().equals(SystemRole.ADMIN));
        LOGGER.info("Logged user role is " + loggedUser.getRoles());

        Checking account = checkingRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        if(!isAdmin || !loggedUser.accessAllAccounts().contains(account)){
            LOGGER.error("The logged user is not an admin or owner of the account");
            throw new AuthenticationErrorException("The user is not the owner nor an admin");
        }
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());
        if(account.getMinimumBalance().getAmount().compareTo(account.getBalance().getAmount()) > 0 && !account.isBelowMinimumBalance()){
            LOGGER.info("The account balance is below the minimum so the penalty fee is applied");
            account.getBalance().decreaseAmount(account.getPenaltyFee());
            account.setBelowMinimumBalance(true);
            checkingRepository.save(account);
        }
        LOGGER.info("[END] - checkBalance(Checking)");
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - debitBalance(Checking)");
        Checking account = checkingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().decreaseAmount(amount);
        if(account.getMinimumBalance().getAmount().compareTo(account.getBalance().getAmount()) > 0 && !account.isBelowMinimumBalance()){
            LOGGER.info("The account balance is below the minimum so the penalty fee is applied");
            account.getBalance().decreaseAmount(account.getPenaltyFee());
            account.setBelowMinimumBalance(true);
            checkingRepository.save(account);
        }
        checkingRepository.save(account);
        LOGGER.info("[END] - debitBalance(Checking)");
    }

    public void creditBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - creditBalance(Checking)");
        Checking account = checkingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().increaseAmount(amount);

        if(account.isBelowMinimumBalance() && account.getBalance().getAmount().compareTo(account.getMinimumBalance().getAmount()) > 0){
            LOGGER.info("The account balance has been updated above the minimum balance");
            account.setBelowMinimumBalance(false);
        }
        checkingRepository.save(account);
        LOGGER.info("[END] - creditBalance(Checking)");
    }
}
