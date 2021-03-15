package com.cryptoloan.repository.trackers;

import com.cryptoloan.domain.trackers.LoanTypeTracker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanTypeTrackerRepository extends CrudRepository<LoanTypeTracker,Long> {
    @Query
    List<LoanTypeTracker> findByLoanTypeId(@Param("id") Long id);

}
