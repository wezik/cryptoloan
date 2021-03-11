package com.cryptoloan.service;

import com.cryptoloan.domain.dto.ExchangeRatesDto;
import com.cryptoloan.external.api.exchangerates.client.ExchangeRatesClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ExchangeRatesServiceTest {

    @InjectMocks
    private ExchangeRatesService exchangeRatesService;

    @Mock
    private ExchangeRatesClient exchangeRatesClient;

    @Test
    void shouldReturnOptional() {
        // Given
        Optional<ExchangeRatesDto> exchangeRatesDto = Optional.empty();
        Mockito.when(exchangeRatesClient.fetchExchangeRatesForEur()).thenReturn(exchangeRatesDto);
        Mockito.when(exchangeRatesClient.fetchExchangeRatesForCurrency("NONE")).thenReturn(exchangeRatesDto);

        // When
        Optional<ExchangeRatesDto> result = exchangeRatesService.getExchangeRatesForCurrency("NONE");
        Optional<ExchangeRatesDto> result2 = exchangeRatesService.getExchangeRatesForEur();

        // Then
        assertNotNull(result);
        assertNotNull(result2);
        assertFalse(result.isPresent());
        assertFalse(result2.isPresent());
    }

}
