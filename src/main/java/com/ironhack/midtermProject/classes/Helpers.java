package com.ironhack.midtermProject.classes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class Helpers {

    public static Integer calculateYears(LocalDate date){
        Period age = Period.between(date, LocalDate.now());
        return age.getYears();
    }

    public static Integer calculateDays(LocalDate lastUpdate){
        Period age = Period.between(lastUpdate, LocalDate.now());
        return age.getDays();
    }

    public static Integer calculateMonths(LocalDate lastUpdate){
        Period age = Period.between(lastUpdate, LocalDate.now());
        return age.getMonths();
    }
}
