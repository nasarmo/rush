package com.thescore.rush.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thescore.rush.model.Filter;
import com.thescore.rush.model.Rush;
import com.thescore.rush.repository.RushRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RushService {

    @Autowired
    RushRepository rushRepository;

    public List<Rush> getPlayers(){
        return rushRepository.findAll();
    }

    public List<Rush> getAllFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Rush> rushes =  objectMapper.readValue(new ClassPathResource("rushing.json").getInputStream(), new TypeReference<List<Rush>>(){});
        rushRepository.saveAll(rushes);
        return rushes;
    }

    public List<String> getFilters(){
        List<String> filters = new ArrayList<>();
        Filter[] filterArr = Filter.class.getEnumConstants();
        for (int i = 0; i < filterArr.length; i++){
            filters.add(filterArr[i].getLabel());
        }
        return filters;
    }

}
