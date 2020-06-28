package com.ironhack.midtermProject.service;

import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.repository.TransferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class FraudService {

    @Autowired
    TransferenceRepository transferenceRepository;

    public boolean firstCondition(NewTransference newTransference){


        System.out.println("PRIMERO");
        BigDecimal sumLastDayAmounts = transferenceRepository.sumLastDayTransferences();
        System.out.println("SEGUNDO");
        List<BigDecimal> sumOfTransferenceByDay = transferenceRepository.sumOfTransferenceByDay(newTransference.getOriginId());
        System.out.println("TERCERO");
       /* BigDecimal max = sumOfTransferenceByDay.stream().max(BigDecimal::compareTo).get();

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

        */

        return true;
    }
}
