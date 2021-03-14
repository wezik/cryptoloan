package com.cryptoloan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedQuery(
        name = "Loan.findAllBeforeFinalDate",
        query = "FROM loans WHERE DATEDIFF(CURDATE(),final_date) <=0"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity(name="loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="loan_type_id", referencedColumnName = "id", nullable = false)
    private LoanType loanType;

    @Column(name="initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "final_date",nullable = false)
    private LocalDate finalDate;

    @Column(name = "amount_borrowed", nullable = false)
    private String amountBorrowed;

    @Column(name = "amount_to_pay", nullable = false)
    private String amountToPay;

    @Column(name = "currency_borrowed",nullable = false)
    private String currencyBorrowed;

    @Column(name = "currency_paid_in",nullable = false)
    private String currencyPaidIn;

    @Column(name = "installments_paid")
    private Integer installmentsPaid;

    @Column(name = "installments_created")
    private Integer installmentsCreated;

    @Column(name = "installments_total", nullable = false)
    private Integer installmentsTotal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Installment> installmentList;

}
