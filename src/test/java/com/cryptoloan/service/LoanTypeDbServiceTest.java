package com.cryptoloan.service;

import com.cryptoloan.domain.LoanType;
import com.cryptoloan.repository.LoanTypeRepository;
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
class LoanTypeDbServiceTest {

    @InjectMocks
    private LoanTypeDbService loanTypeDbService;

    @Mock
    private LoanTypeRepository loanTypeRepository;

    @Test
    void shouldReturnEmptyList() {
        // Given
        List<LoanType> loanTypesList = new ArrayList<>();
        Mockito.when(loanTypeRepository.findAll()).thenReturn(loanTypesList);

        // When
        List<LoanType> resultList = loanTypeDbService.getAll();

        // Then
        assertNotNull(resultList);
        assertEquals(resultList.size(),0);
    }

    @Test
    void shouldReturnOptional() {
        // Given
        Optional<LoanType> loanType = Optional.empty();
        Mockito.when(loanTypeRepository.findById(1L)).thenReturn(loanType);

        // When
        Optional<LoanType> result = loanTypeDbService.get(1L);

        // Then
        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnSaved() {
        LoanType loanType = new LoanType();
        Mockito.when(loanTypeRepository.save(Mockito.any())).thenReturn(loanType);

        // When
        LoanType result = loanTypeDbService.save(loanType);

        // Then
        assertNotNull(result);
        assertEquals(result,loanType);
    }

}
