package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.exceptions.AccountNotFoundException;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import com.ironhack.midtermProject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        StudentChecking account = checkingRepository.findById(id).orElseThrow(()-> new AccountNotFoundException("The Checking Account has not been found"));
        account.getBalance().increaseAmount(amount);
        studentCheckingRepository.save(account);
    }


}
