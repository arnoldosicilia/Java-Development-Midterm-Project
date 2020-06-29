package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.repository.TransferenceRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class FraudService {

    @Autowired
    TransferenceRepository transferenceRepository;
    private final Logger LOGGER = LogManager.getLogger(FraudService.class);

    public boolean firstCondition(NewTransference newTransference){
        LOGGER.info("[INIT] - firstCondition() Fraud Detection");
        BigDecimal sumLastDayAmounts = transferenceRepository.sumLastDayTransferences(newTransference.getOriginId());
        List<BigDecimal> sumOfTransferenceByDay = transferenceRepository.sumOfTransferenceByDay(newTransference.getOriginId());

        BigDecimal max = sumOfTransferenceByDay.stream().max(BigDecimal::compareTo).get();

        System.out.println(max);
        System.out.println(newTransference.getAmount());

        boolean result;

        if (max.multiply(new BigDecimal("1.5")).compareTo(newTransference.getAmount()) > 0 ) {
            result = true;
        } else {
            result = false;
        }
        System.out.println(result);
        return result;
    }

    public boolean secondCondition(NewTransference newTransference){

        boolean result;

        List<Transference> transferences = transferenceRepository.lastSecondTransferences(newTransference.getOriginId());
        if(transferences.size() != 0 ){
            result = false;
        }else{
            result = true;
        }
        return result;
    }


}
