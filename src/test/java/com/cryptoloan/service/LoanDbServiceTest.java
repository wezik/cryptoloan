package com.cryptoloan.service;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.repository.LoanRepository;
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
class LoanDbServiceTest {

    @InjectMocks
    private LoanDbService loanDbService;

    @Mock
    private LoanRepository loanRepository;

    @Test
    void shouldReturnEmptyList() {
        // Given
        List<Loan> loanList = new ArrayList<>();
        Mockito.when(loanRepository.findAll()).thenReturn(loanList);
        Mockito.when(loanRepository.findAllBeforeFinalDate()).thenReturn(loanList);

        // When
        List<Loan> resultList = loanDbService.getAll();
        List<Loan> resultList2 = loanDbService.getAllActive();

        // Then
        assertNotNull(resultList);
        assertEquals(resultList.size(),0);
        assertNotNull(resultList2);
        assertEquals(resultList2.size(),0);
    }

    @Test
    void shouldReturnOptional() {
        // Given
        Optional<Loan> loan = Optional.empty();
        Mockito.when(loanRepository.findById(1L)).thenReturn(loan);

        // When
        Optional<Loan> result = loanDbService.get(1L);

        // Then
        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnSaved() {
        Loan loan = new Loan();
        Mockito.when(loanRepository.save(Mockito.any())).thenReturn(loan);

        // When
        Loan result = loanDbService.save(loan);

        // Then
        assertNotNull(result);
        assertEquals(result,loan);
    }

}
