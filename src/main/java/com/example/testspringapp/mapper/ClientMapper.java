package com.example.testspringapp.mapper;

import com.example.testspringapp.dto.ClientDto;
import com.example.testspringapp.model.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrgLegalFormMapper orgLegalFormMapper;

    public ClientDto convertToClientDto(Client client) {
        ClientDto clientDto = mapper.map(client, ClientDto.class);
        clientDto.setOrgLegalFormDto(orgLegalFormMapper.convertToOgrLegalFormDto(client.getOrgLegalForm()));
        return clientDto;
    }

    public Client convertToClient(ClientDto clientDto) {
        Client client = mapper.map(clientDto, Client.class);
        client.setOrgLegalForm(orgLegalFormMapper.convertToOgrLegalForm(clientDto.getOrgLegalFormDto()));
        return client;
    }
}
