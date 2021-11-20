package com.example.testspringapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClientDto {

    private Long id;
    private String name;
    private String shortName;
    private String address;
    @JsonProperty("orgLegalForm")
    private OrgLegalFormDto orgLegalFormDto;
}
