package com.thescore.rush.service;

import com.thescore.rush.model.Rush;
import com.thescore.rush.repository.RushRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DownloadService {

    @Autowired
    RushRepository rushRepository;

    public void downloadCsv(HttpServletResponse response, List<Rush> rushes) {
        String filename = "rushSet.csv";
        try {
            response.setContentType("text/csv");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + filename + "\"");
            CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(),
                    CSVFormat.DEFAULT.withHeader("Player","Team","Pos","Att/G","Att","Yds","Avg","Yds/G","TD",
                            "Lng","1st","1st%","20+", "40+", "FUM"
                    ));
            for (Rush rush : rushes) {
                csvPrinter.printRecord(Arrays.asList(rush.getPlayer(), rush.getTeam(),rush.getPosition(),rush.getAttemptPerGame(),rush.getAttempt(),
                        rush.getTotalYards(),rush.getAverage(),rush.getYardsPerGame(),rush.getTouchDowns(),
                        rush.getLongest(),rush.getFirstDown(),rush.getTwentyYardsPlus(),rush.getFourtyYardsPlus(),rush.getFumbles()));
            }
            csvPrinter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

