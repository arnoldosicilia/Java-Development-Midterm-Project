package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.dto.CreateStudentChecking;
import com.ironhack.midtermProject.model.StudentChecking;
import com.ironhack.midtermProject.service.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingControllerImpl {

    @Autowired
    StudentCheckingService studentCheckingService;

    @PostMapping("/student-checking")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking create(@RequestBody CreateStudentChecking createStudentChecking) {return studentCheckingService.create(createStudentChecking);}

}
