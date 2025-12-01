package me.bhradec.day1.part2;

public class Dial {
    private final int minValue;
    private final int maxValue;

    private int currentPosition;
    private final ZeroDetector zeroDetector;

    public Dial(
        final int minValue,
        final int maxValue,
        final int startPosition,
        final ZeroDetector zeroDetector) {

        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentPosition = startPosition;
        this.zeroDetector = zeroDetector;
    }

    public void turnLeft(final int clicks) {
        for (int i = 0; i < clicks; i++) {
            currentPosition--;

            if (currentPosition == minValue - 1) {
                currentPosition = maxValue;
            }

            if (currentPosition == 0) {
                zeroDetector.handleZeroDetected();
            }
        }
    }

    public void turnRight(final int clicks) {
        for (int i = 0; i < clicks; i++) {
            currentPosition++;

            if (currentPosition == maxValue + 1) {
                currentPosition = minValue;
            }

            if (currentPosition == 0) {
                zeroDetector.handleZeroDetected();
            }
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }
}
