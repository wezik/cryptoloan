package com.cryptoloan.mapper;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.dto.LoanTypeDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoanTypeMapperTest {

    private final LoanTypeMapper loanTypeMapper = new LoanTypeMapper();

    @Test
    void testMapToLoanType() {
        // Given
        LoanTypeDto loanTypeDto = new LoanTypeDto(1L,"Test",35,7.5,12.5,"100","25000");
        List<Loan> loans = List.of(
                new Loan(),
                new Loan()
        );

        // When
        LoanType result = loanTypeMapper.mapToLoanType(loanTypeDto,loans);

        // Then
        assertNotNull(result);
        assertEquals(result.getId(),loanTypeDto.getId());
        assertEquals(result.getName(),loanTypeDto.getName());
        assertEquals(result.getTimePeriod(),loanTypeDto.getTimePeriod());
        assertEquals(result.getInterest(),loanTypeDto.getInterest());
        assertEquals(result.getPunishment(),loanTypeDto.getPunishment());
        assertEquals(result.getMinAmount(),loanTypeDto.getMinAmount());
        assertEquals(result.getMaxAmount(),loanTypeDto.getMaxAmount());
        assertEquals(result.getLoans().size(),loans.size());

    }

    @Test
    void testMapToLoanTypeDto() {
        // Given
        LoanType loanType = new LoanType(1L,"Test",35,7.5,12.5,"100","25000", new ArrayList<>());

        // When
        LoanTypeDto result = loanTypeMapper.mapToLoanTypeDto(loanType);

        // Then
        assertNotNull(result);
        assertEquals(result.getId(),loanType.getId());
        assertEquals(result.getName(),loanType.getName());
        assertEquals(result.getTimePeriod(),loanType.getTimePeriod());
        assertEquals(result.getInterest(),loanType.getInterest());
        assertEquals(result.getPunishment(),loanType.getPunishment());
        assertEquals(result.getMinAmount(),loanType.getMinAmount());
        assertEquals(result.getMaxAmount(),loanType.getMaxAmount());

    }

    @Test
    void testMapToLoanTypeDtoList() {
        // Given
        List<LoanType> loanTypes = List.of(
                new LoanType(1L,"Test",35,7.5,12.5,"100","25000", new ArrayList<>()),
                new LoanType(2L,"Test",35,7.5,12.5,"100","25000", new ArrayList<>()),
                new LoanType(3L,"Test",35,7.5,12.5,"100","25000", new ArrayList<>())

        );

        // When
        List<LoanTypeDto> result = loanTypeMapper.mapToLoanTypeDtoList(loanTypes);

        // Then
        assertNotNull(result);
        for (int i=0; i<result.size(); i++) {
            assertEquals(result.get(i).getId(),loanTypes.get(i).getId());
            assertEquals(result.get(i).getName(),loanTypes.get(i).getName());
            assertEquals(result.get(i).getTimePeriod(),loanTypes.get(i).getTimePeriod());
            assertEquals(result.get(i).getInterest(),loanTypes.get(i).getInterest());
            assertEquals(result.get(i).getPunishment(),loanTypes.get(i).getPunishment());
            assertEquals(result.get(i).getMinAmount(),loanTypes.get(i).getMinAmount());
            assertEquals(result.get(i).getMaxAmount(),loanTypes.get(i).getMaxAmount());
        }

    }

}
