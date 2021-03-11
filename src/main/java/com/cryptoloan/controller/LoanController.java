package com.cryptoloan.controller;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.dto.LoanDto;
import com.cryptoloan.exception.LoanNotFoundException;
import com.cryptoloan.mapper.LoanMapper;
import com.cryptoloan.service.InstallmentDbService;
import com.cryptoloan.service.LoanDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoanController {

    private final LoanDbService loanDbService;
    private final InstallmentDbService installmentDbService;

    private final LoanMapper loanMapper;

    @GetMapping("loans/{id}")
    public LoanDto getLoan(@PathVariable Long id) throws LoanNotFoundException {
        return loanMapper.mapToLoanDto(loanDbService.get(id).orElseThrow(LoanNotFoundException::new));
    }

    @GetMapping("loans")
    public List<LoanDto> listLoans() {
        return loanMapper.mapToLoanDtoList(loanDbService.getAll());
    }

    @PostMapping("loans")
    public void addLoan(@RequestBody LoanDto loanDto) {
        loanDbService.save(loanMapper.mapToLoan(loanDto));
    }

    @PutMapping("loans")
    public LoanDto updateLoan(@RequestBody LoanDto loanDto) throws LoanNotFoundException {
        Loan loan = loanDbService.get(loanDto.getId()).orElseThrow(LoanNotFoundException::new);
        Loan saved = loanDbService.save(loanMapper.mapToLoan(loanDto,loan.getInstallmentList()));
        return loanMapper.mapToLoanDto(saved);
    }

    @DeleteMapping("loans/{id}")
    public void deleteLoan(@PathVariable Long id) throws LoanNotFoundException {
        Loan loan = loanDbService.get(id).orElseThrow(LoanNotFoundException::new);
        loan.getInstallmentList().forEach(installmentDbService::delete);
        loan.setInstallmentList(Collections.emptyList());
        loanDbService.delete(loan);
    }

}
