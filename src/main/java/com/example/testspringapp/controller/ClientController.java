package com.example.testspringapp.controller;

import com.example.testspringapp.dto.ApiResponse;
import com.example.testspringapp.dto.ClientDto;
import com.example.testspringapp.mapper.ClientMapper;
import com.example.testspringapp.model.Client;
import com.example.testspringapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @GetMapping()
    public ApiResponse<List<ClientDto>> findAll(@RequestParam(value = "field", required = false) String field,
                                                @RequestParam(value = "keyword", required = false) String keyword) {
        List<Client> clients = findClients(field, keyword);
        List<ClientDto> clientDtoList = new ArrayList<>();
        clients.forEach(client -> clientDtoList.add(clientMapper.convertToClientDto(client)));
        return new ApiResponse<>(clientDtoList.size(), clientDtoList);
    }

    @GetMapping("/{id}")
    public ApiResponse<ClientDto> showForm(@PathVariable("id") Long id) {
        ClientDto clientDto = clientMapper.convertToClientDto(clientService.findById(id));
        return new ApiResponse<>(1, clientDto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return "redirect:clients/";
    }

    @GetMapping("/{id}/edit")
    public ApiResponse<ClientDto> showEditForm(@PathVariable("id") Long id) {
        ClientDto clientDto = clientMapper.convertToClientDto(clientService.findById(id));
        return new ApiResponse<>(1, clientDto);
    }

    @PatchMapping("/{id}/edit")
    public ApiResponse<ClientDto> update(@PathVariable("id") Long id, @RequestBody ClientDto clientDTO) {
        Client client = clientMapper.convertToClient(clientDTO);
        client.setId(id);
        client = clientService.save(client);
        ClientDto clientDto = clientMapper.convertToClientDto(client);
        return new ApiResponse<>(1, clientDto);
    }

    @GetMapping("/new")
    public ApiResponse<ClientDto> showCreateForm() {
        return new ApiResponse<>(1, new ClientDto());
    }

    @PostMapping("/new")
    public ApiResponse<ClientDto> create(@RequestBody ClientDto clientDTO) {
        Client client = clientMapper.convertToClient(clientDTO);
        client = clientService.save(client);
        ClientDto clientDto = clientMapper.convertToClientDto(client);
        return new ApiResponse<>(1, clientDto);
    }

    private List<Client> findClients(String field, String keyword) {
        List<Client> clients;
        if (field == null && keyword == null) {
            clients = clientService.findAll();
        } else if (field != null && keyword == null) {
            clients = clientService.findAllWithSorting(field);
        } else {
            clients = clientService.findAllWithSortingAndFilters(field, keyword);
        }
        return clients;
    }
}
