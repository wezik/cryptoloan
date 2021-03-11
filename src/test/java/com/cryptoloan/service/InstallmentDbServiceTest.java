package com.cryptoloan.service;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.repository.InstallmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InstallmentDbServiceTest {

    @InjectMocks
    private InstallmentDbService installmentDbService;

    @Mock
    private InstallmentRepository installmentRepository;

    @Test
    void shouldReturnEmptyList() {
        // Given
        List<Installment> installmentList = new ArrayList<>();
        Mockito.when(installmentRepository.findAllUnpaidByDaysFromNow(30)).thenReturn(installmentList);
        Mockito.when(installmentRepository.findAllUnpaid()).thenReturn(installmentList);
        Mockito.when(installmentRepository.findAll()).thenReturn(installmentList);
        Mockito.when(installmentRepository.findAllByLoanId(Mockito.any())).thenReturn(installmentList);

        // When
        List<Installment> result1 = installmentDbService.getAll();
        List<Installment> result2 = installmentDbService.getAllUnpaid();
        List<Installment> result3 = installmentDbService.getAllUnpaidFor(30);
        List<Installment> result4 = installmentDbService.getAllByLoanId(1L);

        // Then
        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
        assertNotNull(result4);
        assertEquals(result1.size(),0);
        assertEquals(result2.size(),0);
        assertEquals(result3.size(),0);
        assertEquals(result4.size(),0);
    }

    @Test
    void shouldReturnOptional() {
        // Given
        Optional<Installment> installment = Optional.empty();
        Mockito.when(installmentRepository.findById(1L)).thenReturn(installment);

        // When
        Optional<Installment> result = installmentDbService.get(1L);

        // Then
        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnSaved() {
        // Given
        Installment installment = new Installment();
        Mockito.when(installmentRepository.save(Mockito.any())).thenReturn(installment);

        // When
        Installment result = installmentDbService.save(installment);

        // Then
        assertNotNull(result);
        assertEquals(result,installment);
    }

}
