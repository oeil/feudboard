package org.teknux.feudboard.util;

import java.time.Duration;
import java.time.LocalDateTime;


/**
 * @author Francois EYL
 */
public class StopWatch {

    private LocalDateTime start;
    private LocalDateTime stop;

    public LocalDateTime start() {
        start = LocalDateTime.now();
        stop = null;
        return start;
    }

    public Duration stop() {
        stop = LocalDateTime.now();
        return getDuration();
    }

    public Duration getDuration() {
        LocalDateTime endTime = stop;
        if (endTime == null) {
            endTime = LocalDateTime.now();
        }
        return Duration.between(start, endTime);
    }

    public static StopWatch get() {
        StopWatch sw = new StopWatch();
        sw.start();
        return sw;
    }
}
