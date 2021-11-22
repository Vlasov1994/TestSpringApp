package com.example.testspringapp.repository;

import com.example.testspringapp.model.Contribution;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    @Query("select c from Contribution c where concat(c.id, c.name, c.client.name," +
                                                     "c.bank.name, c.openingDate," +
                                                     "c.percent, c.termInMonths) like %?1%")
    List<Contribution> findAll(Sort sort, String keyword);
}
