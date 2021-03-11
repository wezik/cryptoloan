package com.cryptoloan.service;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.external.api.exchangerates.client.ExchangeRatesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private final ExchangeRatesClient exchangeRatesClient;

    public Optional<ExchangeRatesDto> getExchangeRatesForEur() {
        return exchangeRatesClient.fetchExchangeRatesForEur();
    }

    public Optional<ExchangeRatesDto> getExchangeRatesForCurrency(String currency) {
        return exchangeRatesClient.fetchExchangeRatesForCurrency(currency);
    }

}
