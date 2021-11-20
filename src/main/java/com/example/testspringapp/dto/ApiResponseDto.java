package com.example.testspringapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDto<T> {

    private int code;
    private String message;
    private T response;

    public ApiResponseDto(int code, T response) {
        this.code = code;
        this.response = response;
    }
}
