package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.controller.dto.create.CreateAccountHolder;
import com.ironhack.midtermProject.enums.SystemRole;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Role;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final Logger LOGGER = LogManager.getLogger(AccountHolderService.class);

    public AccountHolder create(CreateAccountHolder createAccountHolder){
        LOGGER.info("[INIT] - create(AccountHolder)");
        String encodedPassword = passwordEncoder.encode(createAccountHolder.getPassword());

        AccountHolder accountHolder = new AccountHolder(createAccountHolder.getName(),
                createAccountHolder.getUsername(),
                encodedPassword,
                createAccountHolder.getDateOfBirth(),
                new Address(createAccountHolder.getPrimaryAddressDirection(), createAccountHolder.getPrimaryAddressNumber()));

        accountHolder.addRole(new Role(SystemRole.ACCOUNT_HOLDER, accountHolder));
        LOGGER.info("[END] - create(AccountHolder)");
        return accountHolderRepository.save(accountHolder);
    }


}
