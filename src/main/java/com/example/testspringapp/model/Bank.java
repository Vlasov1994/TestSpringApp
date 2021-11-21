package com.example.testspringapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bank")
public class Bank implements InterfaceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "bic")
    private String bic;
}
