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

    /**
     * Returns a the sum of the transferences amount done by a given id account in the last day
     * **/
    @Query(value = "SELECT SUM(amount)\n" +
            "FROM    transference\n" +
            "WHERE origin_account = :id\n" +
            "AND   transference_date >= NOW() - INTERVAL 1 DAY",
            nativeQuery = true)
    public BigDecimal sumLastDayTransferences(Long id);

    /**
     * Returns a List of the transferences done by a given id account in the last second
     * Has 3601 seconds because an adjustment on the UTC configuration
     * **/
    @Query(value = "SELECT * \n" +
            "FROM    transference\n" +
            "WHERE origin_account = :id\n" +
            "AND   transference_date >= NOW() - INTERVAL 3601 SECOND",
            nativeQuery = true)
    public List<Transference> lastSecondTransferences(Long id);

    /**
     * Returns a List of sums of the transferences amounts done by a given id account in
     * **/
    @Query(value = "SELECT SUM(amount), DAY(transference_date) \n" +
            "FROM transference\n" +
            "WHERE origin_account = :id\n" +
            "GROUP BY DAY(transference_date)",
            nativeQuery = true)
    public List<BigDecimal> sumOfTransferenceByDay(Long id);


}
