package com.cryptoloan.controller;

import com.cryptoloan.domain.LoanType;
import com.cryptoloan.domain.dto.LoanTypeDto;
import com.cryptoloan.exception.LoanTypeNotFoundException;
import com.cryptoloan.mapper.LoanTypeMapper;
import com.cryptoloan.service.LoanTypeDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoanTypeController {

    private final LoanTypeDbService loanTypeDbService;

    private final LoanTypeMapper loanTypeMapper;

    @GetMapping("loanTypes/{id}")
    public LoanTypeDto getLoanType(@PathVariable Long id) throws LoanTypeNotFoundException {
        return loanTypeMapper.mapToLoanTypeDto(loanTypeDbService.get(id).orElseThrow(LoanTypeNotFoundException::new));
    }

    @GetMapping("loanTypes")
    public List<LoanTypeDto> listLoanTypes() {
        return loanTypeMapper.mapToLoanTypeDtoList(loanTypeDbService.getAll());
    }

    @PostMapping("loanTypes")
    public void addLoanType(@RequestBody LoanTypeDto loanTypeDto) {
        loanTypeDbService.save(loanTypeMapper.mapToLoanType(loanTypeDto));
    }

    @PutMapping("loanTypes")
    public LoanTypeDto updateLoanType(@RequestBody LoanTypeDto loanTypeDto) throws LoanTypeNotFoundException {
        LoanType loanType = loanTypeDbService.get(loanTypeDto.getId()).orElseThrow(LoanTypeNotFoundException::new);
        LoanType updated = loanTypeDbService.save(loanTypeMapper.mapToLoanType(loanTypeDto,loanType.getLoans()));
        return loanTypeMapper.mapToLoanTypeDto(updated);
    }

    @DeleteMapping("loanTypes/{id}")
    public void deleteLoanType(@PathVariable Long id) throws LoanTypeNotFoundException {
        loanTypeDbService.delete(loanTypeDbService.get(id).orElseThrow(LoanTypeNotFoundException::new));
    }
}
