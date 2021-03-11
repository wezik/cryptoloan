package com.cryptoloan.facade;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.service.BlockChainService;
import com.cryptoloan.service.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExchangeCalculatorFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeCalculatorFacade.class);

    private final ExchangeRatesService exchangeRatesService;
    private final BlockChainService blockChainService;

    public BigDecimal getBuyValueForEur(String currency) {
        if (currency.equalsIgnoreCase("EUR")) return BigDecimal.ONE;
        else if (currency.equalsIgnoreCase("BTC")) return blockChainService.getBitcoinExchange("EUR",BigDecimal.ONE);
        Optional<ExchangeRatesDto> exchangeRatesOptional = exchangeRatesService.getExchangeRatesForEur();
        if (exchangeRatesOptional.isPresent()) {
            return exchangeRatesOptional.get().getRates().get(currency.toUpperCase());
        }
        LOGGER.warn("Failed to fetch currency exchange");
        return BigDecimal.ZERO;
    }

    public BigDecimal getCurrencyExchangeForBitcoin(String currency) {
        if (currency.equalsIgnoreCase("BTC")) return BigDecimal.ONE;
        if (currency.equalsIgnoreCase("EUR")) return blockChainService.getBitcoinExchange("EUR", BigDecimal.ONE);
        Optional<ExchangeRatesDto> rates = exchangeRatesService.getExchangeRatesForCurrency(currency);
        if (rates.isPresent()) {
            return blockChainService.getBitcoinExchange("EUR",rates.get().getRates().get("EUR"));
        }
        LOGGER.warn("Failed to fetch currency exchange");
        return BigDecimal.ZERO;
    }

    private BigDecimal getBuyValueForBitcoin(String currency) {
        if (currency.equalsIgnoreCase("BTC")) return BigDecimal.ONE;
        return getBuyValueForEur(currency).multiply(blockChainService.getBitcoinBuyValueForEur());
    }

    public BigDecimal getExchangeValueInCurrency(String givenCurrency, String responseCurrency) {
        if (responseCurrency.equalsIgnoreCase("BTC")) return getCurrencyExchangeForBitcoin(givenCurrency);
        else if (givenCurrency.equalsIgnoreCase(responseCurrency)) return BigDecimal.ONE;
        else if (givenCurrency.equalsIgnoreCase("BTC")) {
            return getBuyValueForBitcoin(responseCurrency);
        }
        Optional<ExchangeRatesDto> exchangeRates = exchangeRatesService.getExchangeRatesForCurrency(givenCurrency);
        if (exchangeRates.isPresent()) {
            return exchangeRates.get().getRates().get(responseCurrency.toUpperCase());
        }
        LOGGER.warn("Failed to fetch currency exchange");
        return BigDecimal.ZERO;
    }

}
