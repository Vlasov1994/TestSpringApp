package com.example.testspringapp.service;

import com.example.testspringapp.model.Client;
import com.example.testspringapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends AbstractService<Client> {

    @Autowired
    public ClientService(@Qualifier("clientRepository") JpaRepository<Client, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Client> findAllWithSortingAndFilters(String field, String keyword) {
        field = field == null || field.equals("") ? "id" : field;
        return ((ClientRepository) jpaRepository).findAll(Sort.by(field), keyword);
    }
}
