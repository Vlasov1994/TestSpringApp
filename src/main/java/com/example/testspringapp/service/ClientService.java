package com.example.testspringapp.service;

import com.example.testspringapp.model.Client;
import com.example.testspringapp.repository.ClietnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClietnRepository clietnRepository;

    public Client findById(Long id) {
        return clietnRepository.findById(id).orElse(null);
    }

    public List<Client> findAll() {
        return clietnRepository.findAll();
    }

    public List<Client> findAllWithSorting(String field) {
        return clietnRepository.findAll(Sort.by(field));
    }

    public List<Client> findAllWithSortingAndFilters(String field, String keyword) {
        field = field == null || field.equals("") ? "id" : field;
        return clietnRepository.findAll(Sort.by(field), keyword);
    }

    public Client save(Client client) {
        return clietnRepository.save(client);
    }

    public void deleteById(Long id) {
        clietnRepository.deleteById(id);
    }
}
