package com.cryptoloan.service;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanDbService {

    private final LoanRepository loanRepository;

    public Optional<Loan> get(Long id) {
        return loanRepository.findById(id);
    }

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public List<Loan> getAllActive() {
        return loanRepository.findAllBeforeFinalDate();
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public void delete(Loan loan) {
        loanRepository.delete(loan);
    }

}
