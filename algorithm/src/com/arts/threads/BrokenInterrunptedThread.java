package com.arts.threads;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

//使用堵塞队列取消中断
public class BrokenInterrunptedThread extends Thread{

    private static volatile boolean on = true;

    private final BlockingQueue<BigInteger> queue;

    public BrokenInterrunptedThread(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (on) {
                for (int i=0; i<40; i++) {
                    queue.put(p = p.nextProbablePrime());
                    System.out.println(Thread.currentThread().getName() + ": value->" + p);
                }
            }
        } catch (InterruptedException e) {
            //修改1处达到正确中断线程
            return;
        }
    }

    public void cancel() {
        on = false;
    }


    static class Consumer extends Thread{

        private final BlockingQueue<BigInteger> queue;

        public Consumer(BlockingQueue<BigInteger> q) {
            this.queue = q;
        }

        public void run() {
            try {
                while(on && !Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + ": consumer->" + queue.take());
                }
                System.out.println("Work done!");
            } catch (InterruptedException e){
                //修改2处达到正确中断线程
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>(5);
        BrokenInterrunptedThread producer = new BrokenInterrunptedThread(queue);

        producer.start();

        TimeUnit.SECONDS.sleep(1);

        new Consumer(queue).start();
        TimeUnit.SECONDS.sleep(1);

        producer.cancel();

    }

}


