package com.thescore.rush.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thescore.rush.dto.RushDto;
import com.thescore.rush.model.Filter;
import com.thescore.rush.model.Rush;
import com.thescore.rush.repository.RushRepository;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RushService {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    @Autowired
    RushRepository rushRepository;
    @Autowired
    RushMapperDto rushMapperDto;

    public List<Rush> getPlayers(){
        return rushRepository.findAll();
    }

    public List<Rush> getAllFromJson() {
        List<Rush> rushes = new ArrayList<>();
        try {
            rushes = rushMapperDto.mapFromDto(objectMapper.readValue(new ClassPathResource("rushing.json").getInputStream(), new TypeReference<List<RushDto>>() {
                }));
            rushRepository.saveAll(rushes);
        } catch (IOException e){
            logger.error("Unable to parse Rushing.json file " + e.getMessage());
        }
        return rushes;
    }

    public List<Filter> getFilters(){
        List<Filter> filters = new ArrayList<>();
        Filter[] filterArr = Filter.class.getEnumConstants();
        for (int i = 0; i < filterArr.length; i++){
            filters.add(filterArr[i]);
        }
        return filters;
    }

    public List<RushDto> getFilterData(Filter filter, String order, String player, Integer page, Integer size){
        if (size == null)
            size = (int) rushRepository.count();
        if (player == null || player.isEmpty()) {
            if (filter != null && !filter.equals(Filter.NONE)) {
                return rushMapperDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size, Sort.by(getSortDirection(order), filter.getLabel()))).getContent());
            } else
                return rushMapperDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size)).getContent());
        }
        return rushMapperDto.mapToDto(rushRepository.findByPlayerIgnoreCase(player));
    }

    private Sort.Direction getSortDirection(String order){
        if (order != null && order.equalsIgnoreCase("Desc")) {
            return  Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

}
