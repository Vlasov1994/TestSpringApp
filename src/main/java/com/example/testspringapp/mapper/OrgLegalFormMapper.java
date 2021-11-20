package com.example.testspringapp.mapper;

import com.example.testspringapp.dto.OrgLegalFormDto;
import com.example.testspringapp.model.OrgLegalForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrgLegalFormMapper {

    @Autowired
    private ModelMapper mapper;

    public OrgLegalFormDto convertToOgrLegalFormDto(OrgLegalForm orgLegalForm) {
        return mapper.map(orgLegalForm, OrgLegalFormDto.class);
    }

    public OrgLegalForm convertToOgrLegalForm(OrgLegalFormDto orgLegalFormDto) {
        return mapper.map(orgLegalFormDto, OrgLegalForm.class);
    }
}
