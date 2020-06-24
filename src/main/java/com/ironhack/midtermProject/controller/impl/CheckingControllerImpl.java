package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.CreateChecking;
import com.ironhack.midtermProject.controller.interfaces.CheckingControllerInterface;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.service.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CheckingControllerImpl implements CheckingControllerInterface {

    @Autowired
    CheckingService checkingService;

    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking create(@RequestBody @Valid CreateChecking createChecking){return checkingService.create(createChecking);}

}
