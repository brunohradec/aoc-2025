package me.bhradec.day3.part1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static final String INPUT_FILE_PATH = "/Users/bruno/Downloads/aoc_2025_inputs/day_3_input.txt";
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static List<Integer> bankToBatteries(final String bank) {
        return Arrays.stream(bank.split(""))
            .map(Integer::parseInt)
            .toList();
    }

    public static int findMaxIndex(final List<Integer> numbers, final int startIndex) {
        int currentMax = numbers.get(startIndex);
        int maxIndex = startIndex;

        for (int i = startIndex; i < numbers.size(); i++) {
            if (numbers.get(i) > currentMax) {
                currentMax = numbers.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    public static int findMaxJoltage(final String bank) {
        log.info("Processing bank {}", bank);

        List<Integer> batteries = bankToBatteries(bank);

        int maxIndex = findMaxIndex(batteries, 0);

        int firstDigit;
        int secondDigit;

        /* If the largest value is last value in the list, then there's no
         * next largest value for the second digit of the resulting joltage.
         * In that case, the last value is the largest possible second digit,
         * and the second-largest value in the list is the first digit. */
        if (maxIndex == bank.length() - 1) {
            List<Integer> reversedValues = batteries.reversed();
            firstDigit = reversedValues.get(findMaxIndex(reversedValues, 1));
            secondDigit = batteries.get(maxIndex);
        } else {
            firstDigit =  batteries.get(maxIndex);
            secondDigit = batteries.get(findMaxIndex(batteries, maxIndex + 1));
        }

        String maxJoltage = String.valueOf(firstDigit * 10 + secondDigit);

        log.info("Max joltage: {}", maxJoltage);

        return Integer.parseInt(maxJoltage);
    }

    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get(INPUT_FILE_PATH))) {
            long result = lines.mapToInt(Main::findMaxJoltage).sum();
            log.info("Result: {}", result);
        } catch (IOException exception) {
            log.error("Error reading input file", exception);
        }
    }
}
