package com.cryptoloan.updater;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.trackers.CurrencyTracker;
import com.cryptoloan.domain.trackers.LoanTypeTracker;
import com.cryptoloan.service.LoanDbService;
import com.cryptoloan.service.TrackersDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TrackersUpdater {

    private final TrackersDbService trackersDbService;
    private final LoanDbService loanDbService;

    public void updateCurrenciesTracker() {
        List<Loan> loans = loanDbService.getAll();
        Map<String,Integer> map = new HashMap<>();
        loans.forEach(e -> {
            if (map.containsKey(e.getCurrencyBorrowed())) {
                map.put(e.getCurrencyBorrowed(),map.get(e.getCurrencyBorrowed())+1);
            } else {
                map.put(e.getCurrencyBorrowed(),1);
            }
        });

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            List<CurrencyTracker> trackers = trackersDbService.getCurrencyTrackerByCurrency(e.getKey());
            if (trackers.size()==0) {
                trackersDbService.saveCurrencyTracker(new CurrencyTracker(null,e.getKey(),e.getValue()));
            } else {
                CurrencyTracker tracker = trackers.get(0);
                tracker.setTimesChosen(e.getValue());
                trackersDbService.saveCurrencyTracker(tracker);
            }
        }
    }

    public void updateLoanTypesTracker() {
        List<Loan> loans = loanDbService.getAll();
        Map<LoanType,Integer> map = new HashMap<>();
        loans.forEach(e -> {
            LoanType loanType = e.getLoanType();
            if (map.containsKey(loanType)) {
                map.put(loanType,map.get(loanType)+1);
            } else {
                map.put(loanType,1);
            }
        });

        for (Map.Entry<LoanType, Integer> e : map.entrySet()) {
            List<LoanTypeTracker> trackers = trackersDbService.getLoanTypeTrackerByLoanTypeId(e.getKey().getId());
            if (trackers.size()==0) {
                trackersDbService.saveLoanTypeTracker(new LoanTypeTracker(null,e.getKey(),e.getValue()));
            } else {
                LoanTypeTracker tracker = trackers.get(0);
                tracker.setTimesChosen(e.getValue());
                trackersDbService.saveLoanTypeTracker(tracker);
            }
        }
    }

}
