package com.ironhack.midtermProject.controller.interfaces;

import com.ironhack.midtermProject.controller.dto.create.CreateChecking;
import com.ironhack.midtermProject.model.StudentChecking;

public interface CheckingControllerInterface {
    public StudentChecking create(CreateChecking createChecking);
}

