package com.example.testspringapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "org_legal_form")
public class OrgLegalForm implements InterfaceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "ident")
    private String ident;
}
