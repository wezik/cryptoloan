package com.cryptoloan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InstallmentDto {
    private Long id;
    private LocalDate localDate;
    private String amountInBorrowed;
    private String amountInPaid;
    private boolean isPaid;
    private LoanDto loan;
}
