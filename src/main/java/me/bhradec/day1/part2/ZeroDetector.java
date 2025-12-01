package me.bhradec.day1.part2;

public class ZeroDetector {
    private int wrapCount = 0;

    public void handleZeroDetected() {
        this.wrapCount++;
    }

    public int getZeroCount() {
        return this.wrapCount;
    }
}
