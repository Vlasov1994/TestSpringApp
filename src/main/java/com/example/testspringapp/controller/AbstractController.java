package com.example.testspringapp.controller;

import com.example.testspringapp.dto.InterfaceDto;
import com.example.testspringapp.dto.ApiResponseDto;
import com.example.testspringapp.mapper.AbstractMapper;
import com.example.testspringapp.model.InterfaceModel;
import com.example.testspringapp.service.AbstractService;
import com.example.testspringapp.util.Util;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            Dto clientDto = mapper.toDto(service.findById(id));
            ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),
                               "Объект с идентификатором " + id + " не найден.",
                               null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> delete(@PathVariable("id") Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.PERMANENT_REDIRECT.value(),
                               null,
                               "redirect:/"), HttpStatus.PERMANENT_REDIRECT);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),
                               "Не удалось удалить объект. Объект с идентификатором " + id + " не найден.",
                               null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<Dto>> showEditForm(@PathVariable("id") Long id) {
        try {
            Dto clientDto = mapper.toDto(service.findById(id));
            ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),
                               "Объект с идентификатором " + id + " не найден.",
                               null), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<ApiResponseDto<Dto>> update(@PathVariable("id") Long id, @RequestBody Dto clientDTO) {
        try {
            Model model = mapper.toModel(clientDTO);
            model.setId(id);
            model = service.save(model);
            Dto clientDto = mapper.toDto(model);
            ApiResponseDto<Dto> apiResponseDto = new ApiResponseDto<>(HttpStatus.OK.value(), clientDto);
            return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),
                               "Объект с идентификатором " + id + " не найден.",
                               null), HttpStatus.BAD_REQUEST);
        }
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
