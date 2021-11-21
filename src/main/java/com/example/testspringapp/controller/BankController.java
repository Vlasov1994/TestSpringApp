package com.example.testspringapp.controller;

import com.example.testspringapp.dto.ApiResponseDto;
import com.example.testspringapp.dto.BankDto;
import com.example.testspringapp.mapper.BankMapper;
import com.example.testspringapp.model.Bank;
import com.example.testspringapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankMapper bankMapper;

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<BankDto>>> findAll(@RequestParam(value = "field",
                                                                               required = false) String field,
                                                                 @RequestParam(value = "keyword",
                                                                               required = false) String keyword) {
        List<Bank> banks = findBanks(field, keyword);
        List<BankDto> BankDtoList = new ArrayList<>();
        banks.forEach(bank -> BankDtoList.add(bankMapper.toDto(bank)));
        ApiResponseDto<List<BankDto>> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), BankDtoList);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<BankDto>> showForm(@PathVariable("id") Long id) {
        BankDto bankDto = bankMapper.toDto(bankService.findById(id));
        ApiResponseDto<BankDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), bankDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        bankService.deleteById(id);
        return "redirect:banks/";
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<BankDto>> showEditForm(@PathVariable("id") Long id) {
        BankDto bankDto = bankMapper.toDto(bankService.findById(id));
        ApiResponseDto<BankDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), bankDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<BankDto>> update(@PathVariable("id") Long id,
                                                            @RequestBody BankDto bankDto) {
        Bank bank = bankMapper.toModel(bankDto);
        bank.setId(id);
        bank = bankService.save(bank);
        bankDto = bankMapper.toDto(bank);
        ApiResponseDto<BankDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), bankDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<ApiResponseDto<BankDto>> showCreateForm() {
        ApiResponseDto<BankDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), new BankDto());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<BankDto>> create(@RequestBody BankDto bankDto) {
        Bank bank = bankMapper.toModel(bankDto);
        bank = bankService.save(bank);
        bankDto = bankMapper.toDto(bank);
        ApiResponseDto<BankDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), bankDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    
    private List<Bank> findBanks(String field, String keyword) {

        List<Bank> banks;
        if (field == null && keyword == null) {
            banks = bankService.findAll();
        } else if (field != null && keyword == null) {
            banks = bankService.findAllWithSorting(field);
        } else {
            banks = bankService.findAllWithSortingAndFilters(field, keyword);
        }
        return banks;
    }
}
