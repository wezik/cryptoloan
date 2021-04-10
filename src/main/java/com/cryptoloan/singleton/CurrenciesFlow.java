package com.cryptoloan.singleton;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum CurrenciesFlow {

    INSTANCE();

    protected static List<String> values;

    @Component
    public static class CurrenciesInjector {
        @Autowired ExchangeRatesService exchangeRatesService;

        @PostConstruct
        public void postConstruct() {
            values = new ArrayList<>();
            Optional<ExchangeRatesDto> exchangeRatesDto = exchangeRatesService.getExchangeRatesForEur();
            if (exchangeRatesDto.isPresent()) {
                Map<String, BigDecimal> map = exchangeRatesDto.get().getRates();
                for (Map.Entry<String, BigDecimal> e : map.entrySet()) {
                    values.add(e.getKey());
                }
                List<String> addedCurrencies = List.of(
                        "BTC"
                );
                values.addAll(addedCurrencies);
            }
        }
    }

    public static CurrenciesFlow getInstance() {
        return INSTANCE;
    }

    public List<String> getAvailableCurrencies () {
        return values;
    }

}
