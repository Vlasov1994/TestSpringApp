package com.example.testspringapp.controller;

import com.example.testspringapp.mapper.AbstractMapper;
import com.example.testspringapp.service.AbstractService;

import java.util.List;

public abstract class AbstractController<Model, Dto> {

    protected AbstractService<Model> service;

    protected AbstractMapper<Model, Dto> clientMapper;

    protected List<Model> findClients(String field, String keyword) {

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
