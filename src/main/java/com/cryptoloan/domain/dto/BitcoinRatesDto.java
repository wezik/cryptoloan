package com.cryptoloan.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinRatesDto {

    private BigDecimal priceInEUR;

    @JsonProperty("EUR")
    private void unpackPriceInEur(Map<String, Object> value) {
        priceInEUR = BigDecimal.valueOf((Double) value.get("buy"));
    }

}
