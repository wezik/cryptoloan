package com.cryptoloan.controller;

import com.cryptoloan.service.BlockChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/blockchain")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BlockChainController {

    private final BlockChainService blockChainService;

    @GetMapping("rates")
    public BigDecimal getRates(@RequestParam String currency, @RequestParam String value) {
        return blockChainService.getBitcoinExchange(currency,new BigDecimal(value));
    }
}
