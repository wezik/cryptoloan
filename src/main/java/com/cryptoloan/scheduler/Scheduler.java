package com.cryptoloan.scheduler;

import com.cryptoloan.updater.TrackersUpdater;
import com.cryptoloan.validator.InstallmentValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private final InstallmentValidator installmentValidator;
    private final TrackersUpdater trackersUpdater;

    @Scheduled(cron = "0 0 4 * * *")
    //@Scheduled(fixedDelay = 20000)
    public void run() {
        installmentValidator.checkAndCorrectInstallmentCountForAllActive();
        int newInstallments = installmentValidator.createNewInstallments().size();
        int penalizedInstallments = installmentValidator.penalizeAndCorrectOldInstallments().size();
        int reevaluatedInstallments = installmentValidator.reevaluateAndCorrectInstallments().size();
        trackersUpdater.updateCurrenciesTracker();
        trackersUpdater.updateLoanTypesTracker();
        LOGGER.info(
                "CREATED INSTALLMENTS: " +
                        newInstallments +
                        " PENALIZED INSTALLMENTS: " +
                        penalizedInstallments +
                        " REEVALUATED INSTALLMENTS: " +
                        reevaluatedInstallments
        );
        LOGGER.info("DATA TRACKERS UPDATED");
    }

}
