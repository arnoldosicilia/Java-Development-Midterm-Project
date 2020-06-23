package com.ironhack.midtermProject.controller.interfaces;
import com.ironhack.midtermProject.controller.dto.CreateCreditCard;
import com.ironhack.midtermProject.model.CreditCard;

public interface CreditCardControllerInterface {

    public CreditCard create(CreateCreditCard creditCard);
}
