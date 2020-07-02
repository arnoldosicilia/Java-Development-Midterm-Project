package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

public class StudentControllerImpl {

    @Autowired
    StudentCheckingService studentCheckingService;

    @GetMapping("/StudentChecking/{id}/check-balance")
    @ResponseStatus(HttpStatus.OK)
    public ShowBalance checkBalance(@PathVariable Long id, @AuthenticationPrincipal Authentication authentication){return studentCheckingService.checkBalance(id, authentication);}

    @PatchMapping("/admin/StudentChecking/{id}/debit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debitBalance(@PathVariable Long id, @PathVariable BigDecimal amount){studentCheckingService.debitBalance(id, amount);}

    @PatchMapping("/admin/StudentChecking/{id}/credit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void creditBalance(@PathVariable Long id, @PathVariable BigDecimal amount){studentCheckingService.creditBalance(id, amount);}
}
