package me.bhradec.day5.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_5_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        long freshIdCount = 0;

        try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_PATH))) {
            List<String> rangesAndIds = lines.toList();

            int emptyLineIndex = rangesAndIds.indexOf("");

            List<Range> ranges = new ArrayList<>(
                rangesAndIds
                    .subList(0, emptyLineIndex)
                    .stream()
                    .map(line -> line.split("-"))
                    .map(parts -> new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])))
                    .toList()
            );

            // Merge overlapping intervals algorithm

            List<Range> mergedRanges = new ArrayList<>();

            ranges.sort(Comparator.comparingLong(Range::from));

            for (int i = 0; i < ranges.size(); i++) {
                long currentFrom = ranges.get(i).from();
                long currentTo = ranges.get(i).to();

                // Skip already merged ranges
                if (!mergedRanges.isEmpty() && mergedRanges.getLast().to() >= currentTo) {
                    continue;
                }

                // Find the end of the new merged range
                for (int j = i + 1; j < ranges.size(); j++) {
                    if (ranges.get(j).from() <= currentTo) {
                        currentTo = Math.max(currentTo, ranges.get(j).to());
                    }
                }

                mergedRanges.add(new Range(currentFrom, currentTo));
            }

            // Add the number of elements in all the merged ranges
            for (Range range : mergedRanges) {
                freshIdCount += range.to() - range.from() + 1;
            }

            log.info("Result: {}", freshIdCount);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}
