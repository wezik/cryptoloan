package com.cryptoloan.mapper;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.dto.InstallmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstallmentMapper {

    private final LoanMapper loanMapper;

    public Installment mapToInstallment(InstallmentDto installmentDto) {
        return new Installment(installmentDto.getId(),
                installmentDto.getLocalDate(),
                installmentDto.getAmountInBorrowed(),
                installmentDto.getAmountInPaid(),
                installmentDto.isPaid(),
                loanMapper.mapToLoan(installmentDto.getLoan())
        );
    }

    public InstallmentDto mapToInstallmentDto(Installment installment) {
        return new InstallmentDto(installment.getId(),
                installment.getLocalDate(),
                installment.getAmountInBorrowed(),
                installment.getAmountInPaid(),
                installment.isPaid(),
                loanMapper.mapToLoanDto(installment.getLoan())
        );
    }

    public List<InstallmentDto> mapToInstallmentDtoList(List<Installment> installments) {
        return installments.stream()
                .map(this::mapToInstallmentDto)
                .collect(Collectors.toList());
    }

}
