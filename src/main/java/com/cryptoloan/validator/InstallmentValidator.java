package com.cryptoloan.validator;

import com.cryptoloan.config.MainConfig;
import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.Loan;
import com.cryptoloan.facade.ExchangeCalculatorFacade;
import com.cryptoloan.service.InstallmentDbService;
import com.cryptoloan.service.LoanDbService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InstallmentValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(InstallmentValidator.class);

    private final InstallmentDbService installmentDbService;
    private final LoanDbService loanDbService;
    private final ExchangeCalculatorFacade exchangeCalculatorFacade;
    private final MainConfig mainConfig;

    public List<Installment> reevaluateAndCorrectInstallments() {
        LOGGER.info("Reevaluating installments");
        List<Installment> installments = installmentDbService.getAllUnpaid();
        List<Installment> resultList = new ArrayList<>();
        installments.forEach(e -> {
            int scale = e.getLoan().getCurrencyPaidIn().equalsIgnoreCase("BTC") ? 8 : 2;
            e.setAmountInPaid(new BigDecimal(e.getAmountInBorrowed()).multiply(exchangeCalculatorFacade.getExchangeValueInCurrency(
                    e.getLoan().getCurrencyBorrowed(),
                    e.getLoan().getCurrencyPaidIn()),
                    new MathContext(12, RoundingMode.UP)).setScale(scale,RoundingMode.CEILING).toPlainString()
            );
            installmentDbService.save(e);
            resultList.add(e);
        });
        return resultList;
    }

    public void checkAndCorrectInstallmentCountForAllActive() {
        LOGGER.info("Checking for incorrect values of installments count in active loans");
        loanDbService.getAllActive().forEach(this::correctLoan);
    }

    public void checkAndCorrectInstallmentCountFor(Long id) {
        loanDbService.get(id).ifPresent(this::correctLoan);
    }

    private void correctLoan(Loan loan) {
        List<Installment> installments = installmentDbService.getAllByLoanId(loan.getId());
        int created = installments.size();
        int paid = (int) installments.stream()
                .filter(Installment::isPaid)
                .count();
        if (loan.getInstallmentsCreated() != created) {
            loan.setInstallmentsCreated(created);
            loan.setInstallmentsPaid(paid);
            loanDbService.save(loan);
        }
    }

    public List<Installment> penalizeAndCorrectOldInstallments() {
        LOGGER.info("Checking for unpaid installments");
        List<Installment> retiredInstallments = installmentDbService.getAllUnpaidFor(mainConfig.getTimePeriodDays());
        List<Installment> resultList = new ArrayList<>();
        if (retiredInstallments.size()>0) {
            retiredInstallments.forEach(e -> {
                e.setLocalDate(LocalDate.now());
                e.setAmountInBorrowed(
                        new BigDecimal(e.getAmountInBorrowed()).add(
                                new BigDecimal(e.getAmountInBorrowed()).multiply(
                                        BigDecimal.valueOf(e.getLoan().getLoanType().getPunishment() * 0.01)
                                )
                        ).toPlainString()
                );
                installmentDbService.save(e);
                resultList.add(e);
            });
        }
        if (retiredInstallments.size()>0) LOGGER.info("Applied penalty to "+retiredInstallments.size()+" installments");
        return resultList;
    }

    public List<Installment> createNewInstallments() {
        List<Installment> resultList = new ArrayList<>();
        LOGGER.info("Creating new installments");
        List<Loan> activeLoans = loanDbService.getAllActive();
        activeLoans.forEach(e -> {
            int value = (int) (ChronoUnit.DAYS.between(e.getInitialDate(), LocalDate.now()) / mainConfig.getTimePeriodDays());
            if (value != 0 && value > e.getInstallmentsCreated()) {
                BigDecimal totalValue = new BigDecimal(e.getAmountToPay()).divide(new BigDecimal(e.getInstallmentsTotal()),new MathContext(8, RoundingMode.UP));
                BigDecimal currencyExchange = exchangeCalculatorFacade.getExchangeValueInCurrency(e.getCurrencyBorrowed(),e.getCurrencyPaidIn());
                Installment installment = new Installment(
                        null,
                        LocalDate.now(),
                        totalValue.toPlainString(),
                        totalValue.multiply(currencyExchange).toPlainString(),
                        false,
                        e
                );
                installmentDbService.save(installment);
                int total = installmentDbService.getAllByLoanId(e.getId()).size();
                e.setInstallmentsCreated(total);
                loanDbService.save(e);
                resultList.add(installment);
            }
        });
        return resultList;
    }

}
