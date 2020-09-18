package com.thescore.rush.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thescore.rush.model.Position;
import com.thescore.rush.model.Rush;

import java.util.ArrayList;
import java.util.List;

public class RushDto {
    @JsonProperty("Player")
    String player;
    @JsonProperty("Team")
    String team;
    @JsonProperty("Pos")
    Position position;
    @JsonProperty("Att")
    Integer attempt;
    @JsonProperty("Att/G")
    double attemptPerGame;
    @JsonProperty("Yds")
    String totalYards;
    @JsonProperty("Avg")
    double average;
    @JsonProperty("Yds/G")
    double yardsPerGame;
    @JsonProperty("TD")
    Integer touchDowns;
    @JsonProperty("Lng")
    String longest;
    @JsonProperty("1st")
    Integer firstDown;
    @JsonProperty("1st%")
    double firstDownPercentage;
    @JsonProperty("20+")
    Integer twentyYardsPlus;
    @JsonProperty("40+")
    Integer fourtyYardsPlus;
    @JsonProperty("FUM")
    Integer fumbles;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public double getAttemptPerGame() {
        return attemptPerGame;
    }

    public void setAttemptPerGame(double attemptPerGame) {
        this.attemptPerGame = attemptPerGame;
    }

    public String getTotalYards() {
        return totalYards;
    }

    public void setTotalYards(String totalYards) {
        this.totalYards = totalYards;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getYardsPerGame() {
        return yardsPerGame;
    }

    public void setYardsPerGame(double yardsPerGame) {
        this.yardsPerGame = yardsPerGame;
    }

    public Integer getTouchDowns() {
        return touchDowns;
    }

    public void setTouchDowns(Integer touchDowns) {
        this.touchDowns = touchDowns;
    }

    public String getLongest() {
        return longest;
    }

    public void setLongest(String longest) {
        this.longest = longest;
    }

    public Integer getFirstDown() {
        return firstDown;
    }

    public void setFirstDown(Integer firstDown) {
        this.firstDown = firstDown;
    }

    public double getFirstDownPercentage() {
        return firstDownPercentage;
    }

    public void setFirstDownPercentage(double firstDownPercentage) {
        this.firstDownPercentage = firstDownPercentage;
    }

    public Integer getTwentyYardsPlus() {
        return twentyYardsPlus;
    }

    public void setTwentyYardsPlus(Integer twentyYardsPlus) {
        this.twentyYardsPlus = twentyYardsPlus;
    }

    public Integer getFourtyYardsPlus() {
        return fourtyYardsPlus;
    }

    public void setFourtyYardsPlus(Integer fourtyYardsPlus) {
        this.fourtyYardsPlus = fourtyYardsPlus;
    }

    public Integer getFumbles() {
        return fumbles;
    }

    public void setFumbles(Integer fumbles) {
        this.fumbles = fumbles;
    }

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
}
