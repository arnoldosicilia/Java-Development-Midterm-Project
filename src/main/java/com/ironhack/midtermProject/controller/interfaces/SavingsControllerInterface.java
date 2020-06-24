package com.ironhack.midtermProject.controller.interfaces;

import com.ironhack.midtermProject.controller.dto.CreateSavings;
import com.ironhack.midtermProject.model.Savings;

public interface SavingsControllerInterface {
    public Savings create(CreateSavings createSavings);
}
