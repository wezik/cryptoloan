package com.cryptoloan.external.api.blockchain.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BlockChainConfig {

    @Value("${blockchain.api.endpoint.prod}")
    private String blockChainApiEndpoint;

}
