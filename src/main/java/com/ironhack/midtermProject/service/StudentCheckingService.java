package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.CreateChecking;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
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

    public StudentChecking create(CreateChecking createStudentChecking,AccountHolder accountHolder1, AccountHolder accountHolder2 ){

        StudentChecking studentChecking = new StudentChecking(accountHolder1,
                new Money(createStudentChecking.getBalance()),
                createStudentChecking.getSecretKey(),
                createStudentChecking.getStatus());

        return studentCheckingRepository.save(studentChecking);
    }
}
