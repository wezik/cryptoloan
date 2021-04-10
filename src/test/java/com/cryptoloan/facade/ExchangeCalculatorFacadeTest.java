package com.cryptoloan.facade;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.service.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ExchangeCalculatorFacadeTest {

    @InjectMocks
    private ExchangeCalculatorFacade exchangeCalculatorFacade;

    @Mock
    private ExchangeRatesService exchangeRatesService;

    @Test
    void shouldReturn0DueToIncorrectCurrency() {
        // Given

        // When
        BigDecimal result1 = exchangeCalculatorFacade.getBuyValueForEur("test");
        BigDecimal result2 = exchangeCalculatorFacade.getCurrencyExchangeForBitcoin("test");


        // Then
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(result1,BigDecimal.ZERO);
        assertEquals(result2,BigDecimal.ZERO);

    }

    @Test
    void shouldReturnCorrectCurrencyExchange() {
        // Given
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put("PLN",BigDecimal.valueOf(4.0));
        Optional<ExchangeRatesDto> exchangeRatesDto = Optional.of(new ExchangeRatesDto(rates,"USD"));
        Mockito.when(exchangeRatesService.getExchangeRatesForCurrency("USD")).thenReturn(exchangeRatesDto);

        // When
        BigDecimal result = exchangeCalculatorFacade.getExchangeValueInCurrency("USD","PLN");

        // Then
        assertNotNull(result);
        assertEquals(result,BigDecimal.valueOf(4.0));

    }

    @Test
    void shouldReturn1DueToTheSameCurrency() {
        // Given

        // When
        BigDecimal result1 = exchangeCalculatorFacade.getBuyValueForEur("EUR");
        BigDecimal result2 = exchangeCalculatorFacade.getCurrencyExchangeForBitcoin("BTC");

        // Then
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(result1,BigDecimal.ONE);
        assertEquals(result2,BigDecimal.ONE);

    }

}
