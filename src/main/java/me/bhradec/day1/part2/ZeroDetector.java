package me.bhradec.day1.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZeroDetector {
    private int zeroCount = 0;
    private final Logger log = LoggerFactory.getLogger(ZeroDetector.class);

    public void handleZeroDetected() {
        log.info("Zero pass detected");
        this.zeroCount++;
    }

    public int getZeroCount() {
        return this.zeroCount;
    }
}
