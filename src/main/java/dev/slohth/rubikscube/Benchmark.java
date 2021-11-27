package dev.slohth.rubikscube;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Benchmark {

    private final TimeUnit unit;
    private final ScheduledExecutorService service;
    private final Runnable runnable;

    private boolean running = false;
    private int time;

    public Benchmark(TimeUnit unit) {
        this.unit = unit;
        this.service = new ScheduledThreadPoolExecutor(6);
        this.runnable = () -> {
            if (running) { this.time++; this.scheduleNext(); }
        };
        this.time = 0;
    }

    public void start() {
        this.time = 0;
        this.running = true;
        this.scheduleNext();
    }

    public int stop() {
        this.running = false;
        return time;
    }

    private void scheduleNext() {
        this.service.schedule(this.runnable, 1, unit);
    }

}
