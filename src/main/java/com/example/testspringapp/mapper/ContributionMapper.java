package com.example.testspringapp.mapper;

import com.example.testspringapp.dto.ContributionDto;
import com.example.testspringapp.model.Contribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContributionMapper extends AbstractMapper<Contribution, ContributionDto>{

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private BankMapper bankMapper;

    @Override
    public ContributionDto toDto(Contribution contribution) {
        ContributionDto contributionDto = mapper.map(contribution, ContributionDto.class);
        contributionDto.setClientDto(clientMapper.toDto(contribution.getClient()));
        contributionDto.setBankDto(bankMapper.toDto(contribution.getBank()));
        return contributionDto;
    }

    @Override
    public Contribution toModel(ContributionDto clientDto) {
        Contribution client = mapper.map(clientDto, Contribution.class);
        client.setClient(clientMapper.toModel(clientDto.getClientDto()));
        client.setBank(bankMapper.toModel(clientDto.getBankDto()));
        return client;
    }
}
