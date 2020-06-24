package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.CreateCreditCard;
import com.ironhack.midtermProject.controller.interfaces.CreditCardControllerInterface;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSOutput;

import javax.validation.Valid;

@RestController
public class CreditCardControllerImpl implements CreditCardControllerInterface {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping("/credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard create(@RequestBody @Valid CreateCreditCard createCreditCard){
            System.out.println(createCreditCard.getPrimaryOwnerId() + " esta llamada es en el controller");
            return creditCardService.create(createCreditCard);}
}
