package com.thescore.rush.dto;

import java.util.List;

public class RushResponse {

    private List<RushDto> rushes;
    private  Integer sizeOfPlayers;

    public RushResponse(List<RushDto> rushes, Integer sizeOfPlayers){
        this.rushes = rushes;
        this.sizeOfPlayers = sizeOfPlayers;
    }

    public List<RushDto> getRushDtos() {
        return rushes;
    }

    public void setRushDtos(List<RushDto> rushes) {
        this.rushes = rushes;
    }

    public Integer getSizeOfPlayers() {
        return sizeOfPlayers;
    }

    public void setSizeOfPlayers(Integer sizeOfPlayers) {
        this.sizeOfPlayers = sizeOfPlayers;
    }
}
