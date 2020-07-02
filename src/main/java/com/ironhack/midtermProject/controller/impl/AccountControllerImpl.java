package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.exceptions.FraudException;
import com.ironhack.midtermProject.exceptions.NotEnoughFundsException;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.model.User;
import com.ironhack.midtermProject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountControllerImpl {

    @Autowired
    AccountService accountService;

    @PostMapping("/account-holder/transference")
    @ResponseStatus(HttpStatus.OK)
    public Transference transfer(@RequestBody NewTransference newTransference, @AuthenticationPrincipal Authentication authentication) throws FraudException, NotEnoughFundsException { return accountService.transfer(newTransference, authentication);}

}
