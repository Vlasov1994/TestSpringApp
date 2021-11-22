package com.example.testspringapp.service;

import com.example.testspringapp.model.OrgLegalForm;
import com.example.testspringapp.repository.OrgLegalFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrgLegalFormService extends AbstractService<OrgLegalForm> {

    @Autowired
    public OrgLegalFormService(@Qualifier("orgLegalFormRepository") JpaRepository<OrgLegalForm, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    public OrgLegalForm findByIdent (String ident) {
        return ((OrgLegalFormRepository) jpaRepository).findByIdent(ident).orElse(null);
    }

    public List<OrgLegalForm> findAllWithSortingAndFilters(String field, String keyword) {
        field = field == null || field.equals("") ? "id" : field;
        return ((OrgLegalFormRepository) jpaRepository).findAll(Sort.by(field), keyword);
    }
}
