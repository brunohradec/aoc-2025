package me.bhradec.day1.part1;

public class Dial {
    private final int minValue;
    private final int maxValue;

    private int currentPosition;

    public Dial(
        final int minValue,
        final int maxValue,
        final int startPosition) {

        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentPosition = startPosition;
    }

    public void turnLeft(final int clicks) {
        for (int i = 0; i < clicks; i++) {
            currentPosition--;

            if (currentPosition == minValue - 1) {
                currentPosition = maxValue;
            }
        }
    }

    public void turnRight(final int clicks) {
        for (int i = 0; i < clicks; i++) {
            currentPosition++;

            if (currentPosition == maxValue + 1) {
                currentPosition = minValue;
            }
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }
}
