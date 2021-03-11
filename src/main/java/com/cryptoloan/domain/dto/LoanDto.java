package com.cryptoloan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanDto {
    private Long id;
    private UserDto user;
    private LoanTypeDto loanType;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private String amountBorrowed;
    private String amountToPay;
    private String currencyBorrowed;
    private String currencyPaidIn;
    private Integer installmentsPaid;
    private Integer installmentsCreated;
    private Integer installmentsTotal;
}
