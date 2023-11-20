package edu.project3;

import edu.project3.logWorkers.LogReader;
import edu.project3.logWorkers.LogRecord;
import edu.project3.reports.LogReport;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArgs = input.split(" ");
        ParseInput.parseInput(inputArgs);

        LogReader logReader = new LogReader();
        List<LogRecord> logs = logReader.readLogs(ParseInput.getLogPath(),
            ParseInput.getFrom(), ParseInput.getTo()
        ).collect(Collectors.toList());

        LogReport.generateReportAndWriteToFile(logs, ParseInput.getLogPath(),
            ParseInput.getFrom(), ParseInput.getTo(), ParseInput.getOutputFormat()
        );
    }
}
