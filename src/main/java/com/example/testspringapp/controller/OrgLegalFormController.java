package com.example.testspringapp.controller;

import com.example.testspringapp.dto.OrgLegalFormDto;
import com.example.testspringapp.mapper.OrgLegalFormMapper;
import com.example.testspringapp.model.OrgLegalForm;
import com.example.testspringapp.service.OrgLegalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orgLegalForms")
public class OrgLegalFormController extends AbstractController<OrgLegalForm, OrgLegalFormDto> {

    @Autowired
    public OrgLegalFormController(OrgLegalFormService orgLegalFormService, OrgLegalFormMapper orgLegalFormMapper) {
        this.service = orgLegalFormService;
        this.mapper = orgLegalFormMapper;
    }
}
