package com.thescore.rush.service;

import com.thescore.rush.dto.RushDto;
import com.thescore.rush.model.Rush;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RushMapperDto {
	
	/**
	 * Transform a list of Rush to RushDto
	 * @param rushes list of Rush to transform
	 * @return list of RushDto
	 */
    public List<RushDto> mapToDto(List<Rush> rushes){
        List<RushDto> rushDtos = new ArrayList<>();
        for (Rush rush : rushes){
            RushDto rushDto = new RushDto();
            rushDto.setAttempt(rush.getAttempt());
            rushDto.setAttemptPerGame(rush.getAttemptPerGame());
            rushDto.setAverage(rush.getAverage());
            rushDto.setFirstDown(rush.getFirstDown());
            rushDto.setFirstDownPercentage(rush.getFirstDownPercentage());
            rushDto.setFourtyYardsPlus(rush.getFourtyYardsPlus());
            rushDto.setFumbles(rush.getFumbles());
            rushDto.setPlayer(rush.getPlayer());
            rushDto.setPosition(rush.getPosition());
            rushDto.setTeam(rush.getTeam());
            rushDto.setLongest(rush.getLongest());
            rushDto.setTwentyYardsPlus(rush.getTwentyYardsPlus());
            rushDto.setYardsPerGame(rush.getYardsPerGame());
            rushDto.setFourtyYardsPlus(rush.getFourtyYardsPlus());
            rushDto.setLongest(rush.getLongest());
            rushDto.setTouchDowns(rush.getTouchDowns());
            rushDto.setTotalYards(rush.getTotalYards());
            rushDtos.add(rushDto);
        }
        return rushDtos;
    }
	
	/**
	 * Transform a list of RushDto to Rush
	 * @param rushDtos list of rushDto to transform
	 * @return list of Rush
	 */
	public List<Rush> mapFromDto(List<RushDto> rushDtos){
        List<Rush> rushes = new ArrayList<>();
        for (RushDto rushDto : rushDtos){
            Rush rush = new Rush();
            rush.setAttempt(rushDto.getAttempt());
            rush.setAttemptPerGame(rushDto.getAttemptPerGame());
            rush.setAverage(rushDto.getAverage());
            rush.setFirstDown(rushDto.getFirstDown());
            rush.setFirstDownPercentage(rushDto.getFirstDownPercentage());
            rush.setFourtyYardsPlus(rushDto.getFourtyYardsPlus());
            rush.setFumbles(rushDto.getFumbles());
            rush.setPlayer(rushDto.getPlayer());
            rush.setPosition(rushDto.getPosition());
            rush.setTeam(rushDto.getTeam());
            rush.setLongest(rushDto.getLongest());
            rush.setTwentyYardsPlus(rushDto.getTwentyYardsPlus());
            rush.setYardsPerGame(rushDto.getYardsPerGame());
            rush.setFourtyYardsPlus(rushDto.getFourtyYardsPlus());
            rush.setLongest(rushDto.getLongest());
            rush.setTouchDowns(rushDto.getTouchDowns());
            rush.setTotalYards(rushDto.getTotalYards());
            rushes.add(rush);
        }
        return rushes;
    }

}
