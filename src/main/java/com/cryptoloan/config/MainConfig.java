package com.cryptoloan.config;

import com.cryptoloan.scheduler.InstallmentsScheduler;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MainConfig {

    @Value("${time.period.days}")
    private int timePeriodDays;

    @Autowired
    private InstallmentsScheduler installmentsScheduler;

    @Bean
    public void onLaunch() {
        installmentsScheduler.run();
    }

}
