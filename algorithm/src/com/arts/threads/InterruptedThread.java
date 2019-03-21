package com.arts.threads;

import java.util.concurrent.TimeUnit;

//unsafe
public class InterruptedThread {

    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepThread(), "sleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyThread(), "busyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        SleepUtil.sleep(5000);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is " + busyThread.isInterrupted());

        SleepUtil.sleep(2000);

    }

    static class SleepUtil {
        public static void sleep(long time) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class SleepThread implements Runnable{
        @Override
        public void run() {
            for(;;){
                try{
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyThread implements Runnable {

        @Override
        public void run() {
            for(;;) {

            }
        }
    }
}


