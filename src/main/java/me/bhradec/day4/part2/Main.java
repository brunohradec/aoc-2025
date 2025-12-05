package me.bhradec.day4.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_4_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final String ROLL = "@";
    private static final String SPACE = ".";

    private static final List<Direction> directions = Arrays.asList(
        new Direction(-1, -1), new Direction(-1, 0), new Direction(-1, 1),
        new Direction(0, -1), new Direction(0, 1),
        new Direction(1, -1), new Direction(1, 0), new Direction(1, 1)
    );

    private static void printDiagram(final String[][] diagram) {
        for (int i = 0; i < diagram.length; i++) {
            for (int j = 0; j < diagram[i].length; j++) {
                System.out.print(diagram[i][j]);
            }
            System.out.println();
        }
    }

    private static int countAdjacentRolls(
        final String[][] diagram,
        final int row,
        final int col) {

        int rows = diagram.length;
        int cols = diagram[0].length;

        int rollCount = 0;

        for (Direction direction : directions) {
            int checkedRow = row + direction.row();
            int checkedCol = col + direction.col();

            if (checkedRow >= 0 && checkedRow < rows && checkedCol >= 0 && checkedCol < cols) {
                if (diagram[checkedRow][checkedCol].equals(ROLL)) {
                    rollCount++;
                }
            }
        }

        return rollCount;
    }

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_PATH))) {
            int totalAccessibleRollCount = 0;

            String[][] diagram = lines.filter(line -> !line.isEmpty())
                .map(line -> line.split(""))
                .toArray(String[][]::new);

            log.info("Loaded diagram");
            printDiagram(diagram);

            while (true) {
                int currentAccessibleRollCount = 0;

                for (int i = 0; i < diagram.length; i++) {
                    for (int j = 0; j < diagram[i].length; j++) {
                        if (diagram[i][j].equals(ROLL) && countAdjacentRolls(diagram, i, j) < 4) {
                            currentAccessibleRollCount++;
                            diagram[i][j] = SPACE;
                        }
                    }
                }

                if (currentAccessibleRollCount == 0) {
                    break;
                }

                totalAccessibleRollCount += currentAccessibleRollCount;
            }


            log.info("Result: {}", totalAccessibleRollCount);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}
