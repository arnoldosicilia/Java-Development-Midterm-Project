package com.ironhack.midtermProject.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class Helpers {

    public static Integer calculateAge(LocalDate dateOfBirth){
        Period age = Period.between(dateOfBirth, LocalDate.now());
        return age.getYears();
    }
}
