package com.cryptoloan.repository;

import com.cryptoloan.domain.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan,Long> {
    List<Loan> findAll();
    Optional<Loan> findById(Long id);
    @Query
    List<Loan> findAllBeforeFinalDate();
}
