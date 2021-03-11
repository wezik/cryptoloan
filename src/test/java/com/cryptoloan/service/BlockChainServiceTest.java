package com.cryptoloan.service;

import com.cryptoloan.external.api.blockchain.client.BlockChainClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BlockChainServiceTest {

    @InjectMocks
    private BlockChainService blockChainService;

    @Mock
    private BlockChainClient blockChainClient;

    @Test
    void shouldReturnCurrencyExchangeForGivenValue() {
        // Given
        Mockito.when(
                blockChainClient.fetchBitcoinExchangeForCurrency("USD", BigDecimal.valueOf(100))
        ).thenReturn(BigDecimal.valueOf(0.001));

        // When
        BigDecimal result = blockChainService.getBitcoinExchange("USD",BigDecimal.valueOf(100));

        // Then
        assertNotNull(result);
        assertEquals(result,BigDecimal.valueOf(0.001));
    }

    @Test
    void shouldReturnBuyPriceForSingleBitcoinInEur() {
        // Given
        Mockito.when(blockChainClient.fetchBitcoinBuyForEur()).thenReturn(BigDecimal.valueOf(50000.25));

        // When
        BigDecimal result = blockChainService.getBitcoinBuyValueForEur();

        // Then
        assertNotNull(result);
        assertEquals(result,BigDecimal.valueOf(50000.25));
    }

}
