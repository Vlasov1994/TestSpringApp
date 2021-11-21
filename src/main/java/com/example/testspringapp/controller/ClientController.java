package com.example.testspringapp.controller;

import com.example.testspringapp.dto.ApiResponseDto;
import com.example.testspringapp.dto.ClientDto;
import com.example.testspringapp.mapper.ClientMapper;
import com.example.testspringapp.model.Client;
import com.example.testspringapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponseDto<List<ClientDto>>> findAll(@RequestParam(value = "field", required = false) String field,
                                                   @RequestParam(value = "keyword", required = false) String keyword) {
        List<Client> clients = findClients(field, keyword);
        List<ClientDto> clientDtoList = new ArrayList<>();
        clients.forEach(client -> clientDtoList.add(clientMapper.convertToClientDto(client)));
        ApiResponseDto<List<ClientDto>> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDtoList);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ClientDto>> showForm(@PathVariable("id") Long id) {
        ClientDto clientDto = clientMapper.convertToClientDto(clientService.findById(id));
        ApiResponseDto<ClientDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        clientService.deleteById(id);
        return "redirect:clients/";
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<ClientDto>> showEditForm(@PathVariable("id") Long id) {
        ClientDto clientDto = clientMapper.convertToClientDto(clientService.findById(id));
        ApiResponseDto<ClientDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<ClientDto>> update(@PathVariable("id") Long id, @RequestBody ClientDto clientDTO) {
        Client client = clientMapper.convertToClient(clientDTO);
        client.setId(id);
        client = clientService.save(client);
        ClientDto clientDto = clientMapper.convertToClientDto(client);
        ApiResponseDto<ClientDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<ApiResponseDto<ClientDto>> showCreateForm() {
        ApiResponseDto<ClientDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), new ClientDto());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<ClientDto>> create(@RequestBody ClientDto clientDTO) {
        Client client = clientMapper.convertToClient(clientDTO);
        client = clientService.save(client);
        ClientDto clientDto = clientMapper.convertToClientDto(client);
        ApiResponseDto<ClientDto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
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
