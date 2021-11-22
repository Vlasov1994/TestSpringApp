package com.example.testspringapp.controller;

import com.example.testspringapp.dto.ContributionDto;
import com.example.testspringapp.mapper.ContributionMapper;
import com.example.testspringapp.model.Contribution;
import com.example.testspringapp.service.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/contribution")
public class ContributionController extends AbstractController<Contribution, ContributionDto>{

    @Autowired
    public ContributionController(ContributionService contributionService, ContributionMapper contributionMapper) {
        this.service = contributionService;
        this.mapper = contributionMapper;
    }
}
