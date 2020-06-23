package com.ironhack.midtermProject.controller.impl;
import com.ironhack.midtermProject.controller.dto.CreateSavings;
import com.ironhack.midtermProject.controller.interfaces.SavingsControllerInterface;
import com.ironhack.midtermProject.model.Savings;
import com.ironhack.midtermProject.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingsControllerImpl implements SavingsControllerInterface {

    @Autowired
    SavingsService savingsService;

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings create(@RequestBody CreateSavings createSavings) {return savingsService.create(createSavings);}



}
