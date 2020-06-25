package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.CreateAccountHolder;
import com.ironhack.midtermProject.controller.interfaces.AccountHolderControllerInterface;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderControllerImpl implements AccountHolderControllerInterface {

    @Autowired
    AccountHolderService accountHolderService;

    @PostMapping("/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder create(@RequestBody CreateAccountHolder createAccountHolder) {return accountHolderService.create(createAccountHolder);}
}
