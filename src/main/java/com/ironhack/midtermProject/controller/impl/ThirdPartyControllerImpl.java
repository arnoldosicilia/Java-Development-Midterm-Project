package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.create.CreateAccountHolder;
import com.ironhack.midtermProject.controller.dto.create.CreateThirdParty;
import com.ironhack.midtermProject.controller.interfaces.AccountHolderControllerInterface;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.ThirdParty;
import com.ironhack.midtermProject.service.AccountHolderService;
import com.ironhack.midtermProject.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThirdPartyControllerImpl{

    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/admin/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty create(@RequestBody CreateThirdParty createThirdParty) {return thirdPartyService.create(createThirdParty);}
}