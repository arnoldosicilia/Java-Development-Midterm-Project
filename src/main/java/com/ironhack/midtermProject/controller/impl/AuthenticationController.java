package com.ironhack.midtermProject.controller.impl;

import com.ironhack.midtermProject.controller.interfaces.AuthenticationControllerInterface;
import com.ironhack.midtermProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController implements AuthenticationControllerInterface {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Authentication login(@AuthenticationPrincipal Authentication authentication){
        return authentication;
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout(HttpServletRequest request){
        return "Bye bye :D" + request.getLocalName();
    }
}
