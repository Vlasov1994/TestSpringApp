package com.example.testspringapp.repository;

import com.example.testspringapp.model.OrgLegalForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgLegalFormRepository extends JpaRepository<OrgLegalForm, Long> {

    Optional<OrgLegalForm> findByIdent(String ident);
}
