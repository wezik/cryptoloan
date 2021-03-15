package com.cryptoloan.repository.trackers;

import com.cryptoloan.domain.trackers.CurrencyTracker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CurrencyTrackerRepository extends CrudRepository<CurrencyTracker,Long> {
    @Query
    List<CurrencyTracker> findByCurrency(@Param("CURRENCY") String currency);
}
