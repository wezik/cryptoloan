package com.cryptoloan.repository;

import com.cryptoloan.domain.Installment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InstallmentRepository extends CrudRepository<Installment,Long> {
    List<Installment> findAll();
    Optional<Installment> findById(Long id);
    @Query
    List<Installment> findAllByLoanId(@Param("LoanId") Long id);
    @Query
    List<Installment> findAllUnpaidByDaysFromNow(@Param("days") int days);
    @Query
    List<Installment> findAllUnpaid();
}
