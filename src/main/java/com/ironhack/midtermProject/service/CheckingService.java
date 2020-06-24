package com.ironhack.midtermProject.service;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.CreateChecking;
import com.ironhack.midtermProject.exceptions.UserNotFoundException;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;

import static com.ironhack.midtermProject.classes.Helpers.calculateAge;

@Service
public class CheckingService {

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    StudentCheckingService studentCheckingService;

    public StudentChecking create(CreateChecking createChecking) throws UserNotFoundException {
        AccountHolder accountHolder1 = accountHolderRepository.findById(createChecking.getPrimaryOwnerId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        AccountHolder accountHolder2 = createChecking.getSecondaryOwnerId() != null ? accountHolderRepository.findById(createChecking.getSecondaryOwnerId()).orElseThrow(() -> new UserNotFoundException("User not found")) : null;

        if (calculateAge(accountHolder1.getDateOfBirth()) < 24) {
            return studentCheckingService.create(createChecking, accountHolder1, accountHolder2);
        } else {
             Checking checking = new Checking(accountHolder1,
                    new Money(createChecking.getBalance()),
                    createChecking.getSecretKey(),
                    createChecking.getStatus()
            );

            if (accountHolder2 != null) {checking.setSecondaryOwner(accountHolder2);}
            checking.setMonthlyMaintenanceFee(new Money(new BigDecimal("250")));
            checking.setMinimumBalance(new Money(new BigDecimal("12")));

            return checkingRepository.save(checking);
        }
    }
}
