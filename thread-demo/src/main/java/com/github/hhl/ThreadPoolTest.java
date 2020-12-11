package com.github.hhl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        //newFixedThreadPool
        ExecutorService executor = Executors.newFixedThreadPool(100);
        //newCachedThreadPool
        ExecutorService excutor1 = Executors.newCachedThreadPool();
        //newSingleThreadPool
        ExecutorService excutor2 = Executors.newSingleThreadExecutor();
        //scheduleThreadPool
        ScheduledExecutorService executor3 = Executors.newSingleThreadScheduledExecutor();
        executor3.schedule(new Thread(new Runnable() {
            public void run() {
                System.out.println("");
            }
        }), 1000, TimeUnit.SECONDS);
    }
}
