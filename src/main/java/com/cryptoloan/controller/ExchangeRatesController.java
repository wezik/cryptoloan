package com.cryptoloan.controller;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.service.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/exchange")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    @GetMapping("rates")
    public ExchangeRatesDto getExchangeRates() {
        return exchangeRatesService.getExchangeRatesForEur().get();
    }
}
