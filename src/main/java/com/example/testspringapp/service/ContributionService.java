package com.example.testspringapp.service;

import com.example.testspringapp.model.Contribution;
import com.example.testspringapp.repository.ContributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContributionService extends AbstractService<Contribution> {

    @Autowired
    public ContributionService(@Qualifier("contributionRepository") JpaRepository<Contribution, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Contribution> findAllWithSortingAndFilters(String field, String keyword) {
        field = field == null || field.equals("") ? "id" : field;
        return ((ContributionRepository) jpaRepository).findAll(Sort.by(field), keyword);
    }
}
