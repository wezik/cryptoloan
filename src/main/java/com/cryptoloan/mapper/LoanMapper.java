package com.cryptoloan.mapper;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.dto.LoanDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanMapper {

    private final UserMapper userMapper;
    private final LoanTypeMapper loanTypeMapper;

    public Loan mapToLoan(LoanDto loanDto, List<Installment> installments) {
        return new Loan(loanDto.getId(),
                userMapper.mapToUser(loanDto.getUser()),
                loanTypeMapper.mapToLoanType(loanDto.getLoanType()),
                loanDto.getInitialDate(),
                loanDto.getFinalDate(),
                loanDto.getAmountBorrowed(),
                loanDto.getAmountToPay(),
                loanDto.getCurrencyBorrowed(),
                loanDto.getCurrencyPaidIn(),
                loanDto.getInstallmentsPaid(),
                loanDto.getInstallmentsCreated(),
                loanDto.getInstallmentsTotal(),
                installments
        );
    }

    public Loan mapToLoan(LoanDto loanDto) {
        return mapToLoan(loanDto, Collections.emptyList());
    }

    public LoanDto mapToLoanDto(Loan loan) {
        return new LoanDto(loan.getId(),
                userMapper.mapToUserDto(loan.getUser()),
                loanTypeMapper.mapToLoanTypeDto(loan.getLoanType()),
                loan.getInitialDate(),
                loan.getFinalDate(),
                loan.getAmountBorrowed(),
                loan.getAmountToPay(),
                loan.getCurrencyBorrowed(),
                loan.getCurrencyPaidIn(),
                loan.getInstallmentsPaid(),
                loan.getInstallmentsCreated(),
                loan.getInstallmentsTotal()
                );
    }

    public List<LoanDto> mapToLoanDtoList(List<Loan> loans) {
        return loans.stream()
                .map(this::mapToLoanDto)
                .collect(Collectors.toList());
    }

}
