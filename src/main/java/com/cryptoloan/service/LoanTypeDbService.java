package com.cryptoloan.service;

import com.cryptoloan.domain.LoanType;
import com.cryptoloan.repository.LoanTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanTypeDbService {

    private final LoanTypeRepository loanTypeRepository;

    public Optional<LoanType> get(Long id) {
        return loanTypeRepository.findById(id);
    }

    public List<LoanType> getAll() {
        return loanTypeRepository.findAll();
    }

    public LoanType save(LoanType loanType) {
        return loanTypeRepository.save(loanType);
    }

    public void delete(LoanType loanType) {
        loanTypeRepository.delete(loanType);
    }

}
