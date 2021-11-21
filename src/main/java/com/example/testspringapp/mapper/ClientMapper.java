package com.example.testspringapp.mapper;

import com.example.testspringapp.dto.ClientDto;
import com.example.testspringapp.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends AbstractMapper<Client, ClientDto>{

    @Autowired
    private OrgLegalFormMapper orgLegalFormMapper;

    @Override
    public ClientDto toDto(Client client) {
        ClientDto clientDto = mapper.map(client, ClientDto.class);
        clientDto.setOrgLegalFormDto(orgLegalFormMapper.toDto(client.getOrgLegalForm()));
        return clientDto;
    }

    @Override
    public Client toModel(ClientDto clientDto) {
        Client client = mapper.map(clientDto, Client.class);
        client.setOrgLegalForm(orgLegalFormMapper.toModel(clientDto.getOrgLegalFormDto()));
        return client;
    }
}
