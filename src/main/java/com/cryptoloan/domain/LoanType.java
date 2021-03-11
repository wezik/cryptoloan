package com.cryptoloan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "loan_types")
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_period", nullable = false)
    private Integer timePeriod;

    @Column(name = "interest", nullable = false)
    private Double interest;

    @Column(name = "punishment", nullable = false)
    private Double punishment;

    @Column(name = "min_amount")
    private String minAmount;

    @Column(name = "max_amount")
    private String maxAmount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanType", cascade = CascadeType.REMOVE)
    private List<Loan> loans;

}
