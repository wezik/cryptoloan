package com.cryptoloan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQuery(
        name = "Installment.findAllByLoanId",
        query = "FROM installments WHERE loan_id = :LoanId"
)

@NamedQuery(
        name = "Installment.findAllUnpaidByDaysFromNow",
        query = "FROM installments WHERE is_paid = false AND DATEDIFF(CURDATE(),date) >= :days"
)

@NamedQuery(
        name = "Installment.findAllUnpaid",
        query = "FROM installments WHERE is_paid = false"
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Entity(name="installments")
public class Installment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="date", nullable = false)
    private LocalDate localDate;

    @Column(name="amount_in_borrowed", nullable = false, precision = 12, scale = 8)
    private String amountInBorrowed;

    @Column(name="amount_in_paid", precision = 12, scale = 8)
    private String amountInPaid;

    @Column(name="is_paid", nullable = false)
    private boolean isPaid;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name="loan_id", nullable = false)
    private Loan loan;
}
