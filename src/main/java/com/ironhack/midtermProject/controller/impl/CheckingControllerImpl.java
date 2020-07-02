package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateChecking;
import com.ironhack.midtermProject.controller.interfaces.CheckingControllerInterface;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.service.CheckingService;
import com.ironhack.midtermProject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class CheckingControllerImpl implements CheckingControllerInterface {

    @Autowired
    CheckingService checkingService;
    @Autowired
    StudentCheckingService studentCheckingService;

    @PostMapping("/admin/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking create(@RequestBody @Valid CreateChecking createChecking){return checkingService.create(createChecking);}

    @GetMapping("/admin/checking")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> findAll(){ return studentCheckingService.findAll();}

    @GetMapping("/admin/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findById(@PathVariable Long id){return studentCheckingService.findById(id);}

    @GetMapping("/checking/{id}/check-balance")
    @ResponseStatus(HttpStatus.OK)
    public ShowBalance checkBalance(@PathVariable Long id, @AuthenticationPrincipal Authentication authentication){return checkingService.checkBalance(id, authentication);}

    @PatchMapping("/admin/checking/{id}/debit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debitBalance(@PathVariable Long id, @PathVariable BigDecimal amount){checkingService.debitBalance(id, amount);}

    @PatchMapping("/admin/checking/{id}/credit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void creditBalance(@PathVariable Long id, @PathVariable BigDecimal amount){checkingService.creditBalance(id, amount);}

}
