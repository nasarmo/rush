package com.thescore.rush.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thescore.rush.dto.RushDto;
import com.thescore.rush.dto.RushResponse;
import com.thescore.rush.model.Filter;
import com.thescore.rush.model.Rush;
import com.thescore.rush.repository.RushRepository;
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
    private static final Logger logger = LoggerFactory.getLogger(RushService.class);


    @Autowired
    RushRepository rushRepository;
    @Autowired
    RushMapperDto rushMapperDto;

    public List<Rush> getPlayers(){
        return rushRepository.findAll();
    }
    
    /**
     * Save all players to mem DB from a json file found in resource folder 'rushing.json'
     * @return list of Rush that were just saved from the json file
     */
    public List<Rush> saveAllFromJson() {
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
    
    /**
     * Get a list of all filters from Filter enum class. Example: TOTAL_YARDS or NONE
     * @return list of Filters
     */
    public List<Filter> getFilters(){
        List<Filter> filters = new ArrayList<>();
        Filter[] filterArr = Filter.class.getEnumConstants();
        for (int i = 0; i < filterArr.length; i++){
            filters.add(filterArr[i]);
        }
        return filters;
    }
    
    /**
     * Filter the rush data using different inputs
     * @param filter how to sort the data. Example by TOTAL_YARDS or NONE
     * @param order sort them in assending or descending order
     * @param player name of a specific player to search, if empty return all
     * @param page the current page
     * @param size the size of the page
     * @return the filtered down rush dataset
     */
    public RushResponse getFilterData(Filter filter, String order, String player, Integer page, Integer size){
        Integer totalData = (int) rushRepository.count();
        List<RushDto> rushes;
        if (size == null)
            size = totalData;
        if (player == null || player.isEmpty()) {
            if (filter != null && !filter.equals(Filter.NONE)) {
                rushes = rushMapperDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size, Sort.by(getSortDirection(order), filter.getLabel()))).getContent());
            } else
                rushes = rushMapperDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size)).getContent());
        } else {
            rushes = rushMapperDto.mapToDto(rushRepository.findByPlayerLike("%"+player+"%"));
        }

        if(rushes.size() <= 1)
            totalData = 1;
        else
            totalData = totalData / size;
        return new RushResponse(rushes, totalData);
    }
    
    /**
     * get Sort of ASC or DESC order given a string
     * @param order
     * @return Sort direction
     */
    private Sort.Direction getSortDirection(String order){
        if (order != null && order.equalsIgnoreCase("Asc")) {
            return  Sort.Direction.ASC;
        }
        return Sort.Direction.DESC;
    }

}