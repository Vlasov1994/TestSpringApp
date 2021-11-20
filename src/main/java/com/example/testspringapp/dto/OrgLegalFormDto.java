package com.example.testspringapp.dto;

import com.example.testspringapp.model.OrgLegalForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class OrgLegalFormDto {

    private Long id;
    private String name;
    private String ident;
}
