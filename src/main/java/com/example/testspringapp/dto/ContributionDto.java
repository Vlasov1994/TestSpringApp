package com.example.testspringapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ContributionDto implements InterfaceDto {

    private Long id;
    private String name;
    @JsonProperty("client")
    private ClientDto clientDto;
    @JsonProperty("bank")
    private BankDto bankDto;
    private Date openingDate;
    private float percent;
    private int termInMonths;
}
