package com.cryptoloan.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MainConfig {

    @Value("${time.period.days}")
    private int timePeriodDays;

}
