package me.bhradec.day5.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_5_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        long freshIdCount = 0;

        try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_PATH))) {
            List<String> rangesAndIds = lines.toList();

            int emptyLineIndex = rangesAndIds.indexOf("");

            List<Range> ranges = rangesAndIds
                .subList(0, emptyLineIndex)
                .stream()
                .map(line -> line.split("-"))
                .map(parts -> new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])))
                .toList();

            List<Long> ids = rangesAndIds
                .subList(emptyLineIndex + 1, rangesAndIds.size())
                .stream()
                .map(Long::parseLong)
                .toList();

            for (Long id : ids) {
                for (Range range : ranges) {
                    if (id >= range.from() && id <= range.to()) {
                        freshIdCount++;
                        break;
                    }
                }
            }

            log.info("Result: {}", freshIdCount);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}
