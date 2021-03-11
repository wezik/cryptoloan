package com.cryptoloan.mapper;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.User;
import com.cryptoloan.domain.dto.InstallmentDto;
import com.cryptoloan.domain.dto.LoanDto;
import com.cryptoloan.domain.dto.LoanTypeDto;
import com.cryptoloan.domain.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class InstallmentMapperTest {

    @InjectMocks
    private InstallmentMapper installmentMapper;

    @Mock
    private LoanMapper loanMapper;

    @Test
    void testMapToInstallment() {
        // Given
        InstallmentDto installmentDto = new InstallmentDto(2L,
                LocalDate.now(),
                "1200.00",
                "1300",
                false,
                new LoanDto(1L,
                        new UserDto(5L,
                                "name",
                                "kowalski",
                                "12354",
                                "123"),
                        new LoanTypeDto(1L,
                                "Test",
                                35,
                                7.5,
                                12.5,
                                "100",
                                "25000"),
                        LocalDate.now(),
                        LocalDate.now(),
                        "1000",
                        "1200",
                        "USD",
                        "USD",
                        0,
                        0,
                        12
                ));

        Mockito.when(loanMapper.mapToLoan(Mockito.any(LoanDto.class))).thenReturn(new Loan(1L,
                new User(5L,
                        "name",
                        "kowalski",
                        "12354",
                        "123",
                        new ArrayList<>()),
                new LoanType(1L,
                        "Test",
                        35,
                        7.5,
                        12.5,
                        "100",
                        "25000",
                        new ArrayList<>()),
                LocalDate.now(),
                LocalDate.now(),
                "1000",
                "1200",
                "USD",
                "USD",
                0,
                0,
                12,
                List.of(
                        new Installment(),
                        new Installment()
                )));

        // When
        Installment result = installmentMapper.mapToInstallment(installmentDto);

        // Then
        assertNotNull(result);
        assertEquals(result.getId(),installmentDto.getId());
        assertEquals(result.getAmountInBorrowed(),installmentDto.getAmountInBorrowed());
        assertEquals(result.getAmountInPaid(),installmentDto.getAmountInPaid());
        assertEquals(result.isPaid(),installmentDto.isPaid());
        assertEquals(result.getLocalDate(),installmentDto.getLocalDate());
        assertEquals(result.getLoan().getId(),installmentDto.getLoan().getId());

    }

    @Test
    void testMapToInstallmentDto() {
        // Given
        Installment installment = new Installment(2L,
                LocalDate.now(),
                "1200.00",
                "1300",
                false,
                new Loan(1L,
                        new User(5L,
                                "name",
                                "kowalski",
                                "12354",
                                "123",
                                new ArrayList<>()),
                        new LoanType(1L,
                                "Test",
                                35,
                                7.5,
                                12.5,
                                "100",
                                "25000",
                                new ArrayList<>()),
                        LocalDate.now(),
                        LocalDate.now(),
                        "1000",
                        "1200",
                        "USD",
                        "USD",
                        0,
                        0,
                        12,
                        List.of(
                                new Installment(),
                                new Installment()
                        )
                ));

        Mockito.when(loanMapper.mapToLoanDto(Mockito.any(Loan.class))).thenReturn(new LoanDto(1L,
                new UserDto(5L,
                        "name",
                        "kowalski",
                        "12354",
                        "123"),
                new LoanTypeDto(1L,
                        "Test",
                        35,
                        7.5,
                        12.5,
                        "100",
                        "25000"),
                LocalDate.now(),
                LocalDate.now(),
                "1000",
                "1200",
                "USD",
                "USD",
                0,
                0,
                12));

        // When
        InstallmentDto result = installmentMapper.mapToInstallmentDto(installment);

        // Then
        assertNotNull(result);
        assertEquals(result.getId(),installment.getId());
        assertEquals(result.getAmountInBorrowed(),installment.getAmountInBorrowed());
        assertEquals(result.getAmountInPaid(),installment.getAmountInPaid());
        assertEquals(result.isPaid(),installment.isPaid());
        assertEquals(result.getLocalDate(),installment.getLocalDate());
        assertEquals(result.getLoan().getId(),installment.getLoan().getId());

    }

    @Test
    void testMapToInstallmentDtoList() {
        // Given
        List<Installment> installments = new ArrayList<>();

        Installment installment1 = new Installment(2L,
                LocalDate.now(),
                "1200.00",
                "1300",
                false,
                new Loan(1L,
                        new User(5L,
                                "name",
                                "kowalski",
                                "12354",
                                "123",
                                new ArrayList<>()),
                        new LoanType(1L,
                                "Test",
                                35,
                                7.5,
                                12.5,
                                "100",
                                "25000",
                                new ArrayList<>()),
                        LocalDate.now(),
                        LocalDate.now(),
                        "1000",
                        "1200",
                        "USD",
                        "USD",
                        0,
                        0,
                        12,
                        List.of(
                                new Installment(),
                                new Installment()
                        )
                ));
        Installment installment2 = new Installment(2L,
                LocalDate.now(),
                "1200.00",
                "1300",
                false,
                new Loan(1L,
                        new User(5L,
                                "name",
                                "kowalski",
                                "12354",
                                "123",
                                new ArrayList<>()),
                        new LoanType(1L,
                                "Test",
                                35,
                                7.5,
                                12.5,
                                "100",
                                "25000",
                                new ArrayList<>()),
                        LocalDate.now(),
                        LocalDate.now(),
                        "1000",
                        "1200",
                        "USD",
                        "USD",
                        0,
                        0,
                        12,
                        List.of(
                                new Installment(),
                                new Installment()
                        )
                ));

        Mockito.when(loanMapper.mapToLoanDto(Mockito.any(Loan.class))).thenReturn(new LoanDto(1L,
                new UserDto(5L,
                        "name",
                        "kowalski",
                        "12354",
                        "123"),
                new LoanTypeDto(1L,
                        "Test",
                        35,
                        7.5,
                        12.5,
                        "100",
                        "25000"),
                LocalDate.now(),
                LocalDate.now(),
                "1000",
                "1200",
                "USD",
                "USD",
                0,
                0,
                12));

        installments.add(installment1);
        installments.add(installment2);

        // When
        List<InstallmentDto> result = installmentMapper.mapToInstallmentDtoList(installments);

        // Then
        assertNotNull(result);
        for (int i=0; i<result.size(); i++) {
            assertEquals(result.get(i).getId(), installments.get(i).getId());
            assertEquals(result.get(i).getAmountInBorrowed(), installments.get(i).getAmountInBorrowed());
            assertEquals(result.get(i).getAmountInPaid(), installments.get(i).getAmountInPaid());
            assertEquals(result.get(i).isPaid(), installments.get(i).isPaid());
            assertEquals(result.get(i).getLocalDate(), installments.get(i).getLocalDate());
            assertEquals(result.get(i).getLoan().getId(), installments.get(i).getLoan().getId());
        }

    }

}
