package com.cryptoloan.service;

import com.cryptoloan.domain.trackers.CurrencyTracker;
import com.cryptoloan.domain.trackers.LoanTypeTracker;
import com.cryptoloan.repository.trackers.CurrencyTrackerRepository;
import com.cryptoloan.repository.trackers.LoanTypeTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackersDbService {

    private final CurrencyTrackerRepository currencyTrackerRepository;
    private final LoanTypeTrackerRepository loanTypeTrackerRepository;

    public List<CurrencyTracker> getCurrencyTrackerByCurrency(String currency) {
        return currencyTrackerRepository.findByCurrency(currency.toUpperCase());
    }

    public CurrencyTracker saveCurrencyTracker(CurrencyTracker currencyTracker) {
        return currencyTrackerRepository.save(currencyTracker);
    }

    public List<LoanTypeTracker> getLoanTypeTrackerByLoanTypeId(Long id) {
        return loanTypeTrackerRepository.findByLoanTypeId(id);
    }

    public LoanTypeTracker saveLoanTypeTracker(LoanTypeTracker loanTypeTracker) {
        return loanTypeTrackerRepository.save(loanTypeTracker);
    }

}
