package com.cryptoloan.controller;

import com.cryptoloan.config.MainConfig;
import com.cryptoloan.facade.ExchangeCalculatorFacade;
import com.cryptoloan.singleton.CurrenciesFlow;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("v1/app")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AppController {

    private final MainConfig mainConfig;
    private final ExchangeCalculatorFacade exchangeCalculatorFacade;

    @GetMapping("time")
    public Integer getTime() {
        return mainConfig.getTimePeriodDays();
    }

    @GetMapping("currencies")
    public Set<String> getCurrencies() {
        return new HashSet<String>(CurrenciesFlow.INSTANCE.getAvailableCurrencies());
    }

    @GetMapping("exchange/{currency1}/{currency2}")
    public BigDecimal getExchangeFor(@PathVariable String currency1, @PathVariable String currency2) {
        return exchangeCalculatorFacade.getExchangeValueInCurrency(currency1,currency2);
    }

}
