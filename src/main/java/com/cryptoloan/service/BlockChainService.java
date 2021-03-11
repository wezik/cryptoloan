package com.cryptoloan.service;

import com.cryptoloan.external.api.blockchain.client.BlockChainClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BlockChainService {

    private final BlockChainClient blockChainClient;

    public BigDecimal getBitcoinExchange(String currency, BigDecimal value) {
        return blockChainClient.fetchBitcoinExchangeForCurrency(currency,value);
    }

    public BigDecimal getBitcoinBuyValueForEur() {
        return blockChainClient.fetchBitcoinBuyForEur();
    }
}
