package com.ironhack.midtermProject.controller.interfaces;

import com.ironhack.midtermProject.controller.dto.create.CreateAccountHolder;
import com.ironhack.midtermProject.model.AccountHolder;

public interface AccountHolderControllerInterface {
    public AccountHolder create(CreateAccountHolder createAccountHolder);
}
