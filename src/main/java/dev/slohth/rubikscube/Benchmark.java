package dev.slohth.rubikscube;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Benchmark {

    private final ScheduledExecutorService service;
    private final Runnable runnable;

    private boolean running = false;
    private int millis;

    public Benchmark() {
        this.service = new ScheduledThreadPoolExecutor(6);
        this.runnable = () -> {
            if (running) { this.millis++; this.scheduleNext(); }
        };
        this.millis = 0;
    }

    public void start() {
        this.millis = 0;
        this.running = true;
        this.scheduleNext();
    }

    public int stop() {
        this.running = false;
        return millis;
    }

    private void scheduleNext() {
        this.service.schedule(this.runnable, 1, TimeUnit.MILLISECONDS);
    }

}
