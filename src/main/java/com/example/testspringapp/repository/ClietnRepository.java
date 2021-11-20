package com.example.testspringapp.repository;

import com.example.testspringapp.model.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClietnRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c where concat(c.id, c.name, c.shortName, c.address, c.orgLegalForm.id) like %?1%")
    List<Client> findAll(Sort sort, String keyword);
}
