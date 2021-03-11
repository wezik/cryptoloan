package com.cryptoloan.external.api.exchangerates.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ExchangeRatesConfig {

    @Value("${exchangerates.api.endpoint.prod}")
    private String ExchangeRatesApiEndpoint;

}
