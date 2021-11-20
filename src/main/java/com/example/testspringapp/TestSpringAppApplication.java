package com.example.testspringapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestSpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringAppApplication.class, args);

    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
