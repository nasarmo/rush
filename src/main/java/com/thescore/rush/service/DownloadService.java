package com.thescore.rush.service;

import com.thescore.rush.dto.RushDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DownloadService {

    private static final Logger logger= LoggerFactory.getLogger(DownloadService.class);
    private static final String FILE_NAME = "rushSet.csv";

    public void downloadCsv(HttpServletResponse response, List<RushDto> rushes) {

        logger.debug("Downloading CSV for %s rows of players" , rushes.size());

        try {
            response.setContentType("text/csv");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + FILE_NAME + "\"");
            CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(),
                    CSVFormat.DEFAULT.withHeader("Player","Team","Pos","Att/G","Att","Yds","Avg","Yds/G","TD",
                            "Lng","1st","1st%","20+", "40+", "FUM"
                    ));
            for (RushDto rush : rushes) {
                csvPrinter.printRecord(Arrays.asList(rush.getPlayer(), rush.getTeam(),rush.getPosition(),rush.getAttemptPerGame(),rush.getAttempt(),
                        rush.getTotalYards(),rush.getAverage(),rush.getYardsPerGame(),rush.getTouchDowns(),
                        rush.getLongest(),rush.getFirstDown(),rush.getTwentyYardsPlus(),rush.getFourtyYardsPlus(),rush.getFumbles()));
            }
            csvPrinter.close();
        } catch (IOException e) {
            logger.error("Failed to download CSV file: " + e.getMessage());
        }
    }
}

