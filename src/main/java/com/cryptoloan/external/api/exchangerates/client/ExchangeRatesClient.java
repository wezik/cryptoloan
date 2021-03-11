package com.cryptoloan.external.api.exchangerates.client;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.external.api.exchangerates.config.ExchangeRatesConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExchangeRatesClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExchangeRatesClient.class);

    private final ExchangeRatesConfig exchangeRatesConfig;

    private final RestTemplate restTemplate;

    public Optional<ExchangeRatesDto> fetchExchangeRatesForCurrency(String currency) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(getExchangeRatesForCurrencyURI(currency), ExchangeRatesDto.class));
        } catch (HttpServerErrorException e) {
            LOGGER.warn(e.getMessage());
        }
        return Optional.empty();
    }

    private URI getExchangeRatesForCurrencyURI(String currency) {
        return UriComponentsBuilder.fromHttpUrl(
                exchangeRatesConfig.getExchangeRatesApiEndpoint()
                +"?base="
                +currency.toUpperCase())
                .build().encode().toUri();
    }

    public Optional<ExchangeRatesDto> fetchExchangeRatesForEur() {
        try {
            return Optional.ofNullable(restTemplate.getForObject(getExchangeRatesForEurURI(), ExchangeRatesDto.class));
        } catch (HttpServerErrorException e) {
            LOGGER.warn(e.getMessage());
        }
        return Optional.empty();
    }

    private URI getExchangeRatesForEurURI() {
        return UriComponentsBuilder.fromHttpUrl(exchangeRatesConfig.getExchangeRatesApiEndpoint()).build().encode().toUri();
    }

}
