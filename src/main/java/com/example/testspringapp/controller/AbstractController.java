package com.example.testspringapp.controller;

import com.example.testspringapp.dto.InterfaceDto;
import com.example.testspringapp.dto.ApiResponseDto;
import com.example.testspringapp.mapper.AbstractMapper;
import com.example.testspringapp.model.InterfaceModel;
import com.example.testspringapp.service.AbstractService;
import com.example.testspringapp.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<Model extends InterfaceModel, Dto extends InterfaceDto> {

    protected AbstractService<Model> service;

    protected AbstractMapper<Model, Dto> mapper;

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<Dto>>> findAll(@RequestParam(value = "field",
                                                                           required = false) String field,
                                                             @RequestParam(value = "keyword",
                                                                           required = false) String keyword) {
        List<Model> models = findModel(field, keyword);
        List<Dto> dtos = new ArrayList<>();
        models.forEach(model -> dtos.add(mapper.toDto(model)));
        ApiResponseDto<List<Dto>> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), dtos);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Dto>> showForm(@PathVariable("id") Long id) {
        Dto clientDto = mapper.toDto(service.findById(id));
        ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<Dto>> showEditForm(@PathVariable("id") Long id) {
        Dto clientDto = mapper.toDto(service.findById(id));
        ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<Dto>> update(@PathVariable("id") Long id, @RequestBody Dto clientDTO) {
        Model model = mapper.toModel(clientDTO);
        model.setId(id);
        model = service.save(model);
        Dto clientDto = mapper.toDto(model);
        ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<ApiResponseDto<Dto>> showCreateForm() throws InstantiationException, IllegalAccessException {
        ApiResponseDto<Dto> apiResponseDto =
                new ApiResponseDto<>(HttpStatus.OK.value(),
                                     (Dto) Util.getGenericParameterClass(this.getClass(),
                                                           1).newInstance());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<Dto>> create(@RequestBody Dto clientDto) {
        Model model = mapper.toModel(clientDto);
        model = service.save(model);
        clientDto = mapper.toDto(model);
        ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    protected List<Model> findModel(String field, String keyword) {

        List<Model> model;
        if (field == null && keyword == null) {
            model = service.findAll();
        } else if (field != null && keyword == null) {
            model = service.findAllWithSorting(field);
        } else {
            model = service.findAllWithSortingAndFilters(field, keyword);
        }
        return model;
    }
}
