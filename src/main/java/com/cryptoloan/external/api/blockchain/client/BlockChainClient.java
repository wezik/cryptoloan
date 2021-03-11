package com.cryptoloan.external.api.blockchain.client;

import com.cryptoloan.domain.dto.BitcoinRatesDto;
import com.cryptoloan.external.api.blockchain.config.BlockChainConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlockChainClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockChainClient.class);

    private final BlockChainConfig blockChainConfig;

    private final RestTemplate restTemplate;

    public BigDecimal fetchBitcoinExchangeForCurrency(String currency, BigDecimal value) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(getBitcoinExchangeForCurrencyURI(currency, value), String.class);

            if (response.getStatusCode() == HttpStatus.OK) return new BigDecimal(response.getBody());
        } catch (HttpServerErrorException e) {
            LOGGER.warn(e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal fetchBitcoinBuyForEur() {
        try {
            Optional<BitcoinRatesDto> response = Optional.ofNullable(restTemplate.getForObject(getBitcoinBuyValuesForEur(), BitcoinRatesDto.class));
            if (response.isPresent()) return response.get().getPriceInEUR();
        } catch (HttpServerErrorException e) {
            LOGGER.warn(e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    private URI getBitcoinExchangeForCurrencyURI(String currency, BigDecimal value) {
        return UriComponentsBuilder.fromHttpUrl(blockChainConfig.getBlockChainApiEndpoint()+"/tobtc?currency="
                + currency
                + "&value="
                + value.toString())
                .build().encode().toUri();
    }

    private URI getBitcoinBuyValuesForEur() {
        return UriComponentsBuilder.fromHttpUrl(blockChainConfig.getBlockChainApiEndpoint()+"/ticker").build().encode().toUri();
    }

}
