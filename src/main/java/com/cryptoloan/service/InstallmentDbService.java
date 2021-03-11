package com.cryptoloan.service;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstallmentDbService {

    private final InstallmentRepository installmentRepository;

    public Optional<Installment> get(Long id) {
        return installmentRepository.findById(id);
    }

    public List<Installment> getAll() {
        return installmentRepository.findAll();
    }

    public List<Installment> getAllByLoanId(Long id) {
        return installmentRepository.findAllByLoanId(id);
    }

    public List<Installment> getAllUnpaid() {
        return installmentRepository.findAllUnpaid();
    }

    public List<Installment> getAllUnpaidFor(int days) {
        return installmentRepository.findAllUnpaidByDaysFromNow(days);
    }

    public Installment save(Installment installment) {
        return installmentRepository.save(installment);
    }

    public void delete(Installment installment) {
        installmentRepository.delete(installment);
    }

}
