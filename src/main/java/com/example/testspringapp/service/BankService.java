package com.example.testspringapp.service;

import com.example.testspringapp.model.Bank;
import com.example.testspringapp.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService extends AbstractService<Bank> {

    @Autowired
    public BankService(@Qualifier("bankRepository") JpaRepository<Bank, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    public Bank findByBic (String bic) {
        return ((BankRepository) jpaRepository).findByBic(bic).orElse(null);
    }

    public List<Bank> findAllWithSortingAndFilters(String field, String keyword) {
        field = field == null || field.equals("") ? "id" : field;
        return ((BankRepository) jpaRepository).findAll(Sort.by(field), keyword);
    }
}
