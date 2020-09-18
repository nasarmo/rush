package com.thescore.rush.controller;

import com.thescore.rush.dto.RushDto;
import com.thescore.rush.service.DownloadService;
import com.thescore.rush.service.DtoRushService;
import com.thescore.rush.service.RushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/rush")
public class RushController {

    private final static String DEFAULT_PAGE = "0";
    private final static String DEFAULT_SIZE = "10";

    @Autowired
    private RushService rushService;
    @Autowired
    private DownloadService downloadCsv;
    @Autowired
    private DtoRushService dtoRushService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody List<RushDto> getRush(@RequestParam(required = false) String filter, @RequestParam(required = false) String order,
                                            @RequestParam(required = false) String player, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                               @RequestParam(defaultValue = DEFAULT_SIZE) int size){
        return dtoRushService.getFilterData(filter, order, player, page, size);
    }

    @RequestMapping(value="/getCsv", method = RequestMethod.GET)
    public void downloadCsv(HttpServletResponse httpServletResponse, @RequestParam(required = false) String filter, @RequestParam(required = false) String order,
                            @RequestParam(required = false) String player){
        List<RushDto> rushDto = dtoRushService.getFilterData(filter, order, player, 0, null);
        downloadCsv.downloadCsv(httpServletResponse, rushDto);
    }

    @RequestMapping(value="/getFilters", method = RequestMethod.GET)
    public @ResponseBody List<String> getFilters ()
    {
        return rushService.getFilters();
    }


}
