package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.controller.dto.create.CreateAccountHolder;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Role;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AccountHolder create(CreateAccountHolder createAccountHolder){

        String encodedPassword = passwordEncoder.encode(createAccountHolder.getPassword());

        AccountHolder accountHolder = new AccountHolder(createAccountHolder.getName(),
                createAccountHolder.getUsername(),
                encodedPassword,
                createAccountHolder.getDateOfBirth(),
                new Address(createAccountHolder.getPrimaryAddressDirection(), createAccountHolder.getPrimaryAddressNumber()),
                new Address(createAccountHolder.getMailingAddressDirection(), createAccountHolder.getMailingAddressNumber()));
        accountHolder.addRole(new Role(SystemRole.ACCOUNT_HOLDER, accountHolder));

        return accountHolderRepository.save(accountHolder);
    }


}
