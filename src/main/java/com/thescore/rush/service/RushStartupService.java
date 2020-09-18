package com.thescore.rush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RushStartupService {

    @Autowired
    RushService rushService;

    @PostConstruct
    private void loadRushData() {
        //Can always thread this if the file is very large and is performing slow
        rushService.getAllFromJson();
    }
}
