package com.example.testspringapp.controller;

import com.example.testspringapp.dto.BankDto;
import com.example.testspringapp.mapper.BankMapper;
import com.example.testspringapp.model.Bank;
import com.example.testspringapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banks")
public class BankController extends AbstractController<Bank, BankDto> {

    @Autowired
    public BankController(BankService bankService, BankMapper bankMapper) {
        this.service = bankService;
        this.mapper = bankMapper;
    }
}
