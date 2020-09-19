package com.thescore.rush.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RushStartupService {

    @Autowired
    RushService rushService;
    
    /**
     * Load all the Rush data from a json file in resource folder 'rushing.json'
     */
    @PostConstruct
    private void loadRushData() {
        //Thread this if performing slow
        rushService.saveAllFromJson();
    }
    
}
