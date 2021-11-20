package com.example.testspringapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_legal_form_id")
    private OrgLegalForm orgLegalForm;

    public Client(String name, String shortName, OrgLegalForm orgLegalForm) {
        this.name = name;
        this.shortName = shortName;
        this.orgLegalForm = orgLegalForm;
    }

    public Client() {

    }
}
