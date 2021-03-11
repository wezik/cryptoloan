package com.cryptoloan.repository;

import com.cryptoloan.domain.LoanType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanTypeRepository extends CrudRepository<LoanType,Long> {
    List<LoanType> findAll();
    Optional<LoanType> findById(Long id);
}
