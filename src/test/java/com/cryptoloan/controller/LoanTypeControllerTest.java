package com.cryptoloan.controller;

import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.dto.LoanTypeDto;
import com.cryptoloan.mapper.LoanTypeMapper;
import com.cryptoloan.service.LoanTypeDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringJUnitConfig
@WebMvcTest(LoanTypeController.class)
class LoanTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanTypeDbService loanTypeDbService;

    @MockBean
    private LoanTypeMapper loanTypeMapper;

    @Test
    void shouldReturnLoanTypeDto() throws Exception {
        // Given
        LoanType loanType = new LoanType(2L,
                "Test Loan Type",
                12,
                12.5,
                20.5,
                "1000",
                "15000",
                new ArrayList<>());

        Mockito.when(loanTypeDbService.get(2L)).thenReturn(Optional.of(loanType));

        Mockito.when(loanTypeDbService.save(Mockito.any(LoanType.class))).thenReturn(loanType);

        LoanTypeDto loanTypeDto = new LoanTypeDto(2L,
                "Test Loan Type",
                12,
                12.5,
                20.5,
                "1000",
                "15000");

        Mockito.when(loanTypeMapper.mapToLoanTypeDto(Mockito.any(LoanType.class))).thenReturn(loanTypeDto);
        Mockito.when(loanTypeMapper.mapToLoanType(Mockito.any(LoanTypeDto.class),Mockito.anyList())).thenReturn(loanType);

        Gson gson = new Gson();
        String content = gson.toJson(loanType);

        // When & Then

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/loanTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test Loan Type")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.punishment", Matchers.is(20.5)));

    }

    @Test
    void shouldReturnLoanTypeDtos() throws Exception {
        // Given
        List<LoanType> loanTypes = List.of(
                new LoanType(2L,
                        "Test Loan Type",
                        12,
                        12.5,
                        20.5,
                        "1000",
                        "15000",
                        new ArrayList<>()),
                new LoanType(3L,
                        "Secondary Loan Type",
                        12,
                        12.5,
                        20.5,
                        "1000",
                        "15000",
                        new ArrayList<>()));

        Mockito.when(loanTypeDbService.getAll()).thenReturn(loanTypes);

        List<LoanTypeDto> loanTypeDtos = List.of(
                new LoanTypeDto(2L,
                        "Test Loan Type",
                        12,
                        12.5,
                        20.5,
                        "1000",
                        "15000"),
                new LoanTypeDto(3L,
                        "Secondary Loan Type",
                        12,
                        12.5,
                        20.5,
                        "1000",
                        "15000"));

        Mockito.when(loanTypeMapper.mapToLoanTypeDtoList(Mockito.any())).thenReturn(loanTypeDtos);

        // When & THen
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/loanTypes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Loan Type")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Secondary Loan Type")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].interest", Matchers.is(12.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].maxAmount", Matchers.is("15000")));

    }

}
