package com.ironhack.midtermProject.repository;

import com.ironhack.midtermProject.model.Transference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransferenceRepository extends JpaRepository<Transference, Long> {

    @Query(value = "SELECT SUM(amount)\n" +
            "FROM    transference\n" +
            "WHERE   transference_date >= NOW() - INTERVAL 1 DAY",
            nativeQuery = true)
    public BigDecimal sumLastDayTransferences();


    @Query(value = "SELECT SUM(amount), DAY(transference_date) \n" +
            "FROM transference\n" +
            "WHERE origin_account = :id\n" +
            "GROUP BY DAY(transference_date)",
            nativeQuery = true)
    public List<BigDecimal> sumOfTransferenceByDay(Long id);


}
