package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.controller.dto.create.CreateAccountHolder;
import com.ironhack.midtermProject.controller.dto.create.CreateThirdParty;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.ThirdParty;
import com.ironhack.midtermProject.repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ThirdParty create(CreateThirdParty createThirdParty) {
        ThirdParty thirdParty = new ThirdParty(createThirdParty.getName(), passwordEncoder.encode(createThirdParty.getHashedKey()));
        return thirdPartyRepository.save(thirdParty);
    }

}
