package com.cryptoloan.mapper;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.dto.LoanTypeDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanTypeMapper {

    public LoanType mapToLoanType(LoanTypeDto loanTypeDto, List<Loan> loans) {
        return new LoanType(loanTypeDto.getId(),
                loanTypeDto.getName(),
                loanTypeDto.getTimePeriod(),
                loanTypeDto.getInterest(),
                loanTypeDto.getPunishment(),
                loanTypeDto.getMinAmount(),
                loanTypeDto.getMaxAmount(),
                loans
        );
    }

    public LoanType mapToLoanType(LoanTypeDto loanTypeDto) {
        return mapToLoanType(loanTypeDto, Collections.emptyList());
    }

    public LoanTypeDto mapToLoanTypeDto(LoanType loanType) {
        return new LoanTypeDto(loanType.getId(),
                loanType.getName(),
                loanType.getTimePeriod(),
                loanType.getInterest(),
                loanType.getPunishment(),
                loanType.getMinAmount(),
                loanType.getMaxAmount()
        );
    }

    public List<LoanTypeDto> mapToLoanTypeDtoList(List<LoanType> loanTypes) {
        return loanTypes.stream()
                .map(this::mapToLoanTypeDto)
                .collect(Collectors.toList());
    }

}
