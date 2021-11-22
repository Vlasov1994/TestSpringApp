package com.example.testspringapp.repository;

import com.example.testspringapp.model.Bank;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByBic (String bic);

    @Query("select b from Bank b where concat(b.id, b.name, b.bic) like %?1%")
    List<Bank> findAll(Sort sort, String keyword);
}
