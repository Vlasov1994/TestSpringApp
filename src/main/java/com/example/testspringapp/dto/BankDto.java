package com.example.testspringapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankDto {

    private Long id;
    private String name;
    private String bic;
}
