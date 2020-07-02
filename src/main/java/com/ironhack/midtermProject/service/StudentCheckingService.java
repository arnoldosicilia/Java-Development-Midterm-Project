package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.AuthenticationErrorException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.StudentChecking;
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

@Service
public class StudentCheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    private final Logger LOGGER = LogManager.getLogger(StudentCheckingService.class);

    public List<StudentChecking> findAll(){ return studentCheckingRepository.findAll();}
    public StudentChecking findById(Long id) {return studentCheckingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));}

    public ShowBalance checkBalance(Long id,   Authentication authentication){
        LOGGER.info("[INIT] - checkBalance(StudentChecking)");
        AccountHolder loggedUser = accountHolderRepository.findByUsername(authentication.getName());
        boolean isAdmin = loggedUser.getRoles().stream().anyMatch(x-> x.getRole().equals(SystemRole.ADMIN));
        LOGGER.info("Logged user role is " + loggedUser.getRoles());

        StudentChecking account = studentCheckingRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        if(!isAdmin || !loggedUser.accessAllAccounts().contains(account)){
            LOGGER.error("The logged user is not an admin or owner of the account");
            throw new AuthenticationErrorException("The user is not the owner nor an admin");
        }
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());
        LOGGER.info("[END] - checkBalance(StudentChecking)");
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - debitBalance(StudentChecking)");
        StudentChecking account = studentCheckingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().decreaseAmount(amount);
        studentCheckingRepository.save(account);
        LOGGER.info("[END] - debitBalance(StudentChecking)");

    }

    public void creditBalance(Long id, BigDecimal amount) {
        LOGGER.info("[INIT] - creditBalance(StudentChecking)");
        StudentChecking account = checkingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().increaseAmount(amount);
        studentCheckingRepository.save(account);
        LOGGER.info("[END] - creditBalance(StudentChecking)");

    }


}
