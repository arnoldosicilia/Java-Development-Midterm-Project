package com.ironhack.midtermProject.controller.interfaces;

import com.ironhack.midtermProject.controller.dto.CreateChecking;
import com.ironhack.midtermProject.controller.dto.CreateSavings;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.Savings;

public interface CheckingControllerInterface {
    public Checking create(CreateChecking createChecking);
}

