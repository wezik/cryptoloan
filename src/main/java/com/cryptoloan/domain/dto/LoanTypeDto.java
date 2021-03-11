package com.cryptoloan.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanTypeDto {
    private Long id;
    private String name;
    private Integer timePeriod;
    private Double interest;
    private Double punishment;
    private String minAmount;
    private String maxAmount;
}
