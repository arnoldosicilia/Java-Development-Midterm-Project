package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.CreateStudentChecking;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CreditCardRepository;
import com.ironhack.midtermProject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public StudentChecking create(CreateStudentChecking createStudentChecking){
        AccountHolder accountHolder1 = accountHolderRepository.findById(createStudentChecking.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = accountHolderRepository.findById(createStudentChecking.getPrimaryOwnerId()).orElseThrow(()-> new UserNotFoundException("User not found"));

        StudentChecking studentChecking = new StudentChecking(accountHolder1,
                accountHolder2,
                new Money(new BigDecimal(createStudentChecking.getBalance())),
                new Money(new BigDecimal(createStudentChecking.getPenaltyFee())),
                createStudentChecking.getSecretKey(),
                createStudentChecking.getStatus());

        return studentCheckingRepository.save(studentChecking);
    }
}
