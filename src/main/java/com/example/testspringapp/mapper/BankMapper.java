package com.example.testspringapp.mapper;

import com.example.testspringapp.dto.BankDto;
import com.example.testspringapp.model.Bank;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    @Autowired
    private ModelMapper mapper;

    public BankDto convertToBankDto(Bank orgLegalForm) {
        return mapper.map(orgLegalForm, BankDto.class);
    }

    public Bank convertToBank(BankDto orgLegalFormDto) {
        return mapper.map(orgLegalFormDto, Bank.class);
    }
}
