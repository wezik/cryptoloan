package com.cryptoloan.controller;

import com.cryptoloan.domain.dto.InstallmentDto;
import com.cryptoloan.exception.InstallmentNotFoundException;
import com.cryptoloan.mapper.InstallmentMapper;
import com.cryptoloan.service.InstallmentDbService;
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
        installmentDbService.save(installmentMapper.mapToInstallment(installmentDto));
    }

    @PutMapping("installments")
    public InstallmentDto updateInstallment(@RequestBody InstallmentDto installmentDto) {
        return installmentMapper.mapToInstallmentDto(
                installmentDbService.save(installmentMapper.mapToInstallment(installmentDto))
        );
    }

    @DeleteMapping("installments/{id}")
    public void deleteInstallment(@PathVariable Long id) throws InstallmentNotFoundException {
        installmentDbService.delete(installmentDbService.get(id).orElseThrow(InstallmentNotFoundException::new));
    }

}
