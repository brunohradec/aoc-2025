package me.bhradec.day3.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_3_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static List<Integer> bankToBatteries(final String bank) {
        return Arrays.stream(bank.split(""))
            .map(Integer::parseInt)
            .toList();
    }

    public static String findMaxJoltage(final String bank, final int digits) {
        log.info("Processing bank {}", bank);

        List<Integer> batteries = bankToBatteries(bank);

        // Monotonic stack algorithm
        Stack<Integer> stack = new Stack<>();
        int leftForRemoval = batteries.size() - digits;

        for (int joltage : batteries) {
            while (!stack.isEmpty() && stack.peek() < joltage && leftForRemoval > 0) {
                stack.pop();
                leftForRemoval--;
            }

            stack.push(joltage);
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            resultBuilder.append(stack.get(i));
        }

        String result = resultBuilder.toString();
        log.info("Max joltage: {}", result);

        return result;
    }

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_PATH))) {
            long result = lines
                .map(line -> findMaxJoltage(line, 12))
                .mapToLong(Long::parseLong)
                .sum();

            log.info("Result: {}", result);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}
