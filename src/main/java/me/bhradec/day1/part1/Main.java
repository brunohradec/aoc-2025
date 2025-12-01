package me.bhradec.day1.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_1_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Dial dial = new Dial(0, 99, 50);
        log.info("Dial starts by pointing at {}", dial.getCurrentPosition());

        try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_PATH))) {
            long zeroCount = lines.mapToLong(line -> {
                String direction = line.substring(0, 1);
                int clicks = Integer.parseInt(line.substring(1));

                switch (direction) {
                    case "L" -> dial.turnLeft(clicks);
                    case "R" -> dial.turnRight(clicks);
                    default -> log.error("Cannot parse direction and clicks, skipping");
                }

                log.info("The dial is rotated {} to point at {}", line, dial.getCurrentPosition());

                return (dial.getCurrentPosition() == 0) ? 1 : 0;
            }).sum();

            log.info("Result: {}", zeroCount);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}