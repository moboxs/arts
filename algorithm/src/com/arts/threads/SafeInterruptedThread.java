package com.arts.threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//safe
public class SafeInterruptedThread {

    public static void main(String[] args) throws InterruptedException {

        DateFormat format = new SimpleDateFormat("HH:mm:ss");

        Runner runnerOne = new Runner();
        Thread threadOne = new Thread(runnerOne, "thread one");

        threadOne.start();
        TimeUnit.SECONDS.sleep(1);

        threadOne.interrupt();


        System.out.println("thread one interrupted is " + threadOne.isInterrupted());
        System.out.println("thread one interrupted at " + format.format(new Date()));

        Runner runnerTwo = new Runner();
        Thread threadTwo = new Thread(runnerTwo, "thread two");

        threadTwo.start();
        TimeUnit.SECONDS.sleep(1);

        runnerTwo.cancel();

        System.out.println("thread two interrupted is " + threadTwo.isInterrupted());
        System.out.println("thread two interrupted at " + format.format(new Date()));

    }


    private static class Runner implements Runnable{

        private long i;
        //是否继续执行的标志
        private volatile boolean on = true;

        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Counter i=" + i);
        }

        public void cancel() {
            on = false;
        }
    }

}
