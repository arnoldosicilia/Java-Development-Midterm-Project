package com.ironhack.midtermProject.controller.interfaces;

import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;

public interface AuthenticationControllerInterface {
    Authentication login(Authentication authentication);
    String logout(HttpServletRequest request);
}

