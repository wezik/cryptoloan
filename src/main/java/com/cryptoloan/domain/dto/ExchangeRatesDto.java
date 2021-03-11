package com.cryptoloan.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesDto {

    @JsonProperty("rates")
    Map<String, BigDecimal> rates;

    @JsonProperty("base")
    private String base;

    @JsonProperty("date")
    private LocalDate date;

}
