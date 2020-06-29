package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateCreditCard;
import com.ironhack.midtermProject.controller.interfaces.CreditCardControllerInterface;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class CreditCardControllerImpl implements CreditCardControllerInterface {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("/admin/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@RequestBody @Valid CreateCreditCard createCreditCard){ return creditCardService.create(createCreditCard);}

    @GetMapping("/admin/credit-card")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> findAll(){ return creditCardService.findAll();}

    @GetMapping("/credit-card/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard findById(@PathVariable Long id){ return creditCardService.findById(id);}

    @GetMapping("/credit-card/{id}/check-balance")
    @ResponseStatus(HttpStatus.OK)
    public ShowBalance checkBalance(@PathVariable Long id, @AuthenticationPrincipal Authentication authentication){return creditCardService.checkBalance(id, authentication);}

    @PatchMapping("/admin/credit-card/{id}/debit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debitBalance(@PathVariable Long id, @PathVariable BigDecimal amount){creditCardService.debitBalance(id, amount);}

    @PatchMapping("/admin/credit-card/{id}/credit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void creditBalance(@PathVariable Long id, @PathVariable BigDecimal amount){creditCardService.creditBalance(id, amount);}
}
