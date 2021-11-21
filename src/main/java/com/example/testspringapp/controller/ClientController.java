package com.example.testspringapp.controller;

import com.example.testspringapp.dto.ClientDto;
import com.example.testspringapp.mapper.ClientMapper;
import com.example.testspringapp.model.Client;
import com.example.testspringapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clients")
public class ClientController extends AbstractController<Client, ClientDto>{

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.service = clientService;
        this.mapper = clientMapper;
    }
}
