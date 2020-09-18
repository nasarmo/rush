package com.thescore.rush.service;

import com.thescore.rush.dto.RushDto;
import com.thescore.rush.repository.RushRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoRushService {

    @Autowired
    RushRepository rushRepository;

    public List<RushDto> getFilterData(String filter, String order, String player, Integer page, Integer size){
        if (size == null)
            size = (int) rushRepository.count();
        RushDto rushDto = new RushDto();
        if (player == null || player.isEmpty()) {
            if (filter != null && !filter.equalsIgnoreCase("none")) {
                if (order != null && order.equalsIgnoreCase("Desc"))
                    return rushDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, filter))).getContent());
                else
                    return rushDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, filter))).getContent());
            }
            else
                return rushDto.mapToDto(rushRepository.findAll(PageRequest.of(page, size)).getContent());
        }
        return rushDto.mapToDto(rushRepository.findByPlayerIgnoreCase(player));
    }

}
