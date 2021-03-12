package com.cryptoloan.validator;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.Loan;
import com.cryptoloan.service.InstallmentDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InstallmentValidatorTest {

    @InjectMocks
    private InstallmentValidator installmentValidator;

    @Mock
    private InstallmentDbService installmentDbService;

    @Test
    void shouldReturnCorrectedLoan() {
        // Given
        List<Installment> installments = new ArrayList<>();
        installments.add(new Installment());
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setInstallmentsCreated(0);
        Mockito.when(installmentDbService.getAllByLoanId(1L)).thenReturn(installments);

        // When
        Loan result = installmentValidator.validateInstallmentsForLoan(loan);

        // Then
        assertNotNull(result);
        assertEquals(result.getInstallmentsCreated(),1);
    }

}
