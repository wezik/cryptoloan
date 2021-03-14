package com.cryptoloan.scheduler;

import com.cryptoloan.validator.InstallmentValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstallmentsScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(InstallmentsScheduler.class);

    private final InstallmentValidator installmentValidator;

    @Scheduled(cron = "0 0 4 * * *")
    //@Scheduled(fixedDelay = 20000)
    public void run() {
        installmentValidator.checkAndCorrectInstallmentCount();
        int newInstallments = installmentValidator.createNewInstallments().size();
        int penalizedInstallments = installmentValidator.penalizeAndCorrectOldInstallments().size();
        int reevaluatedInstallments = installmentValidator.reevaluateAndCorrectInstallments().size();
        LOGGER.info(
                "CREATED INSTALLMENTS: " +
                newInstallments +
                " PENALIZED INSTALLMENTS: " +
                penalizedInstallments +
                " REEVALUATED INSTALLMENTS: " +
                reevaluatedInstallments
        );
    }

}