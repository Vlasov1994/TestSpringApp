package com.example.testspringapp.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<T> {

    protected JpaRepository<T, Long> jpaRepository;

    public T findById(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }
    
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    public List<T> findAllWithSorting(String field) {
        return jpaRepository.findAll(Sort.by(field));
    }

    public T save(T t) {
        return jpaRepository.save(t);
    }

    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    public abstract List<T> findAllWithSortingAndFilters(String field, String keyword);
}
