package com.thescore.rush.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Rush {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    String player;
    String team;
    Position position;
    Integer attempt;
    double attemptPerGame;
    String totalYards;
    double average;
    double yardsPerGame;
    Integer touchDowns;
    String longest;
    Integer firstDown;
    double firstDownPercentage;
    Integer twentyYardsPlus;
    Integer fourtyYardsPlus;
    Integer fumbles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
    
}
