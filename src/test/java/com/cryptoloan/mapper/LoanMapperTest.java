package com.cryptoloan.mapper;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.User;
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
class LoanMapperTest {

    @InjectMocks
    private LoanMapper loanMapper;

    @Mock
    private LoanTypeMapper loanTypeMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    void testMapToLoan() {
        // Given
        LoanDto loanDto = new LoanDto(1L,
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
        );
        List<Installment> installments = List.of(
                new Installment(),
                new Installment()
        );

        Mockito.when(userMapper.mapToUser(Mockito.any(UserDto.class))).thenReturn(new User(5L,
                "name",
                "kowalski",
                "12354",
                "123",
                new ArrayList<>()));
        Mockito.when(loanTypeMapper.mapToLoanType(Mockito.any(LoanTypeDto.class))).thenReturn(new LoanType(1L,
                "Test",
                35,
                7.5,
                12.5,
                "100",
                "25000",
                new ArrayList<>()));

        // When
        Loan result = loanMapper.mapToLoan(loanDto,installments);

        // Then
        assertNotNull(result);
        assertEquals(result.getId(),loanDto.getId());
        assertEquals(result.getUser().getId(),loanDto.getUser().getId());
        assertEquals(result.getLoanType().getId(),loanDto.getLoanType().getId());
        assertEquals(result.getInitialDate(),loanDto.getInitialDate());
        assertEquals(result.getFinalDate(),loanDto.getFinalDate());
        assertEquals(result.getAmountBorrowed(),loanDto.getAmountBorrowed());
        assertEquals(result.getAmountToPay(),loanDto.getAmountToPay());
        assertEquals(result.getCurrencyBorrowed(),loanDto.getCurrencyBorrowed());
        assertEquals(result.getCurrencyPaidIn(),loanDto.getCurrencyPaidIn());
        assertEquals(result.getInstallmentsPaid(),loanDto.getInstallmentsPaid());
        assertEquals(result.getInstallmentsCreated(),loanDto.getInstallmentsCreated());
        assertEquals(result.getInstallmentsTotal(),loanDto.getInstallmentsTotal());
        assertEquals(result.getInstallmentList().size(),installments.size());

    }

    @Test
    void testMapToLoanDto() {
        // Given
        Loan loan = new Loan(1L,
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
        );

        Mockito.when(userMapper.mapToUserDto(Mockito.any(User.class))).thenReturn(new UserDto(5L,
                "name",
                "kowalski",
                "12354",
                "123"));
        Mockito.when(loanTypeMapper.mapToLoanTypeDto(Mockito.any(LoanType.class))).thenReturn(new LoanTypeDto(1L,
                "Test",
                35,
                7.5,
                12.5,
                "100",
                "25000"));

        // When
        LoanDto result = loanMapper.mapToLoanDto(loan);

        // Then
        assertNotNull(result);
        assertEquals(result.getId(),loan.getId());
        assertEquals(result.getUser().getId(),loan.getUser().getId());
        assertEquals(result.getLoanType().getId(),loan.getLoanType().getId());
        assertEquals(result.getInitialDate(),loan.getInitialDate());
        assertEquals(result.getFinalDate(),loan.getFinalDate());
        assertEquals(result.getAmountBorrowed(),loan.getAmountBorrowed());
        assertEquals(result.getAmountToPay(),loan.getAmountToPay());
        assertEquals(result.getCurrencyBorrowed(),loan.getCurrencyBorrowed());
        assertEquals(result.getCurrencyPaidIn(),loan.getCurrencyPaidIn());
        assertEquals(result.getInstallmentsPaid(),loan.getInstallmentsPaid());
        assertEquals(result.getInstallmentsCreated(),loan.getInstallmentsCreated());
        assertEquals(result.getInstallmentsTotal(),loan.getInstallmentsTotal());

    }

    @Test
    void testMapToLoanDtoList() {
        // Given
        List<Loan> loans = new ArrayList<>();
        Loan loan1 = new Loan(1L,
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
        );
        Loan loan2 = new Loan(1L,
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
        );

        loans.add(loan1);
        loans.add(loan2);

        Mockito.when(userMapper.mapToUserDto(Mockito.any(User.class))).thenReturn(new UserDto(5L,
                "name",
                "kowalski",
                "12354",
                "123"));
        Mockito.when(loanTypeMapper.mapToLoanTypeDto(Mockito.any(LoanType.class))).thenReturn(new LoanTypeDto(1L,
                "Test",
                35,
                7.5,
                12.5,
                "100",
                "25000"));

        // When
        List<LoanDto> result = loanMapper.mapToLoanDtoList(loans);

        // Then
        assertNotNull(result);
        for (int i=0; i<result.size(); i++) {
            assertEquals(result.get(i).getId(),loans.get(i).getId());
            assertEquals(result.get(i).getUser().getId(),loans.get(i).getUser().getId());
            assertEquals(result.get(i).getLoanType().getId(),loans.get(i).getLoanType().getId());
            assertEquals(result.get(i).getInitialDate(),loans.get(i).getInitialDate());
            assertEquals(result.get(i).getFinalDate(),loans.get(i).getFinalDate());
            assertEquals(result.get(i).getAmountBorrowed(),loans.get(i).getAmountBorrowed());
            assertEquals(result.get(i).getAmountToPay(),loans.get(i).getAmountToPay());
            assertEquals(result.get(i).getCurrencyBorrowed(),loans.get(i).getCurrencyBorrowed());
            assertEquals(result.get(i).getCurrencyPaidIn(),loans.get(i).getCurrencyPaidIn());
            assertEquals(result.get(i).getInstallmentsPaid(),loans.get(i).getInstallmentsPaid());
            assertEquals(result.get(i).getInstallmentsCreated(),loans.get(i).getInstallmentsCreated());
            assertEquals(result.get(i).getInstallmentsTotal(),loans.get(i).getInstallmentsTotal());
        }

    }

}
