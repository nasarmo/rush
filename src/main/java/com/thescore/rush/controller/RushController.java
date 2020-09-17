package com.thescore.rush.controller;

import com.thescore.rush.model.Rush;
import com.thescore.rush.service.DownloadService;
import com.thescore.rush.service.RushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class RushController {

    @Autowired
    private RushService rushService;
    @Autowired
    private DownloadService downloadCsv;

    @RequestMapping(value="/getPlayer", method = RequestMethod.GET)
    public @ResponseBody List<Rush> getPlayer(@RequestParam String player){
        return rushService.getPlayer(player);
    }

    @RequestMapping(value="/totalYards", method = RequestMethod.GET)
    public @ResponseBody
    List<Rush> getPlayersByTotalYards(){
        return rushService.getPlayersByTotalYards();
    }

    @RequestMapping(value="/longest", method = RequestMethod.GET)
    public @ResponseBody
    List<Rush> getPlayersByLongestRush(){
        return rushService.getPlayersByLongestRush();
    }

    @RequestMapping(value="/touchDowns", method = RequestMethod.GET)
    public @ResponseBody
    List<Rush> getPlayersByTotalTouchdowns(){
        return rushService.getPlayersByTotalTouchdowns();
    }

    @RequestMapping(value="loadFromJson", method = RequestMethod.POST)
    public @ResponseBody List<Rush> getAllFromJson() throws IOException {
        return rushService.getAllFromJson();
    }

    @RequestMapping(value="/none", method = RequestMethod.GET)
    public @ResponseBody
    List<Rush> getAllPlayers(){
        return rushService.getPlayers();
    }



    @RequestMapping(value="/getCsv", method = RequestMethod.POST)
    public void downloadCsv(HttpServletResponse httpServletResponse, @RequestBody List<Rush> rushes){
        downloadCsv.downloadCsv(httpServletResponse, rushes);
    }

    @RequestMapping(value="/getFilters", method = RequestMethod.GET)
    public @ResponseBody List<String> getFilters ()
    {
        return rushService.getFilters();
    }


}
