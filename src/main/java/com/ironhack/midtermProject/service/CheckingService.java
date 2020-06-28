package com.ironhack.midtermProject.service;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateChecking;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.*;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import com.ironhack.midtermProject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.ironhack.midtermProject.classes.Helpers.calculateAge;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;


    public StudentChecking create(CreateChecking createChecking) throws UserNotFoundException {
        AccountHolder accountHolder1 = accountHolderRepository.findById(createChecking.getPrimaryOwnerId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = createChecking.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createChecking.getSecondaryOwnerId()).orElseThrow(() -> new UserNotFoundException("User not found")) : null;

        if (calculateAge(accountHolder1.getDateOfBirth()) < 24) {

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

            return checkingRepository.save(checking);
        }
    }

    public List<StudentChecking> findAll(){ return studentCheckingRepository.findAll();}
    public StudentChecking findById(Long id) {return studentCheckingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));}

    public ShowBalance checkBalance(Long id){
        StudentChecking account = studentCheckingRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("The Checking Account has not been found"));
        ShowBalance showBalance = new ShowBalance(account.getId(), account.getBalance().getAmount(), account.getBalance().getCurrency());
        return showBalance;
    }

    public void debitBalance(Long id, BigDecimal amount) {
        StudentChecking account = studentCheckingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().decreaseAmount(amount);
        studentCheckingRepository.save(account);
    }

    public void creditBalance(Long id, BigDecimal amount) {
        StudentChecking account = studentCheckingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().increaseAmount(amount);
        studentCheckingRepository.save(account);
    }
}
