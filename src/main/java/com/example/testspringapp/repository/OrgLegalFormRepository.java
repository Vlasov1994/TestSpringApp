package com.example.testspringapp.repository;

import com.example.testspringapp.model.OrgLegalForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgLegalFormRepository extends JpaRepository<OrgLegalForm, Long> {

    OrgLegalForm findByIdent(String ident);
}
