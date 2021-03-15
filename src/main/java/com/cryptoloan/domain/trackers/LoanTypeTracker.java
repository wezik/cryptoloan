package com.cryptoloan.domain.trackers;

import com.cryptoloan.domain.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQuery(
        name = "LoanTypeTracker.findByLoanTypeId",
        query = "FROM fav_loan_types WHERE loan_type_id = :id"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "fav_loan_types")
public class LoanTypeTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tracker_id")
    private Long tracker_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @Column(name = "times_chosen")
    private int timesChosen;

}
