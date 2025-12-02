package me.bhradec.day2.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_2_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static boolean isInvalidId(String id) {
        int len = id.length();

        if (len % 2 != 0) {
            return false;
        }

        int mid = len / 2;

        return id.substring(0, mid).equals(id.substring(mid));
    }

    public static void main(String[] args) {
        long sum = 0;

        try {
            String input = Files.readString(Paths.get(INPUT_FILE_PATH)).trim();
            List<String> idRanges = List.of(input.split(","));

            for(String idRange : idRanges) {
                Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");
                Matcher matcher = pattern.matcher(idRange);

                if (!matcher.matches()) {
                    return;
                }

                long firstId = Long.parseLong(matcher.group(1));
                long lastId = Long.parseLong(matcher.group(2));

                log.info("Checking range {}-{}", firstId, lastId);

                for (long i = firstId; i <= lastId; i++) {
                    if (isInvalidId(String.valueOf(i))) {
                        log.info("Found invalid ID: {}", i);
                        sum += i;
                    }
                }
            }

            log.info("Result: {}", sum);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}
