package com.example.testspringapp.service;

import com.example.testspringapp.model.Bank;
import com.example.testspringapp.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    
    @Autowired
    private BankRepository bankRepository;

    public Bank findById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    public Bank findByBic (String bic) {
        return bankRepository.findByBic(bic).orElse(null);
    }

    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    public List<Bank> findAllWithSorting(String field) {
        return bankRepository.findAll(Sort.by(field));
    }

    public List<Bank> findAllWithSortingAndFilters(String field, String keyword) {
        field = field == null || field.equals("") ? "id" : field;
        return bankRepository.findAll(Sort.by(field), keyword);
    }

    public Bank save(Bank Bank) {
        return bankRepository.save(Bank);
    }

    public void deleteById(Long id) {
        bankRepository.deleteById(id);
    }
}
