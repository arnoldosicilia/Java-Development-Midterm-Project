package com.ironhack.midtermProject.controller.impl;
import com.ironhack.midtermProject.controller.dto.balance.ShowBalance;
import com.ironhack.midtermProject.controller.dto.create.CreateSavings;
import com.ironhack.midtermProject.controller.interfaces.SavingsControllerInterface;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class SavingsControllerImpl implements SavingsControllerInterface {

    @Autowired
    SavingsService savingsService;

    @PostMapping("/admin/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings create(@RequestBody @Valid CreateSavings createSavings) {return savingsService.create(createSavings);}

    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> findAll(){ return savingsService.findAll();}

    @GetMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings findById(@PathVariable Long id){ return savingsService.findById(id);}

    @GetMapping("/savings/{id}/check-balance")
    @ResponseStatus(HttpStatus.OK)
    public ShowBalance checkBalance(@PathVariable Long id){return savingsService.checkBalance(id);}

    @PatchMapping("/savings/{id}/debit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void debitBalance(@PathVariable Long id, @PathVariable BigDecimal amount){savingsService.debitBalance(id, amount);}

    @PatchMapping("/savings/{id}/credit-balance/{amount}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void creditBalance(@PathVariable Long id, @PathVariable BigDecimal amount){savingsService.creditBalance(id, amount);}


}
