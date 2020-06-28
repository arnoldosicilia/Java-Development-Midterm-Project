package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.repository.TransferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudService {

    @Autowired
    TransferenceRepository transferenceRepository;

    public boolean firstCondition(NewTransference newTransference){

        List<Transference> lastTransferences = transferenceRepository.findAll();
        return true;
    }
}
