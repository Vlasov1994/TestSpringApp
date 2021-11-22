package com.example.testspringapp.repository;

import com.example.testspringapp.model.Bank;
import com.example.testspringapp.model.OrgLegalForm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrgLegalFormRepository extends JpaRepository<OrgLegalForm, Long> {

    Optional<OrgLegalForm> findByIdent(String ident);

    @Query("select olf from OrgLegalForm olf where concat(olf.id, olf.name, olf.ident) like %?1%")
    List<OrgLegalForm> findAll(Sort sort, String keyword);
}
