package com.cryptoloan.controller;

import com.cryptoloan.domain.Installment;
import com.cryptoloan.domain.dto.InstallmentDto;
import com.cryptoloan.exception.InstallmentNotFoundException;
import com.cryptoloan.mapper.InstallmentMapper;
import com.cryptoloan.service.InstallmentDbService;
import com.cryptoloan.validator.InstallmentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class InstallmentController {

    private final InstallmentMapper installmentMapper;
    private final InstallmentDbService installmentDbService;
    private final InstallmentValidator installmentValidator;

    @GetMapping("installments/{id}")
    public InstallmentDto getInstallment(@PathVariable Long id) throws InstallmentNotFoundException {
        return installmentMapper.mapToInstallmentDto(installmentDbService.get(id).orElseThrow(InstallmentNotFoundException::new));
    }

    @GetMapping("installments")
    public List<InstallmentDto> listInstallments() {
        return installmentMapper.mapToInstallmentDtoList(installmentDbService.getAll());
    }

    @PostMapping("installments")
    public void addInstallment(@RequestBody InstallmentDto installmentDto) {
        Installment saved = installmentDbService.save(installmentMapper.mapToInstallment(installmentDto));
        installmentValidator.checkAndCorrectInstallmentCountFor(saved.getLoan().getId());

    }

    @PutMapping("installments")
    public InstallmentDto updateInstallment(@RequestBody InstallmentDto installmentDto) {
        Installment saved = installmentDbService.save(installmentMapper.mapToInstallment(installmentDto));
        installmentValidator.checkAndCorrectInstallmentCountFor(saved.getLoan().getId());
        return installmentMapper.mapToInstallmentDto(saved);
    }

    @DeleteMapping("installments/{id}")
    public void deleteInstallment(@PathVariable Long id) throws InstallmentNotFoundException {
        Installment installment = installmentDbService.get(id).orElseThrow(InstallmentNotFoundException::new);
        installmentDbService.delete(installment);
        installmentValidator.checkAndCorrectInstallmentCountFor(installment.getLoan().getId());
    }

}
