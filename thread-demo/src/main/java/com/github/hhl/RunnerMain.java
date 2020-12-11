package com.github.hhl;

public class RunnerMain {

    public static void main(String[] args) {
        Runner1 r1 = new Runner1();
        Thread t1 = new Thread(r1);
        t1.setName("thread-runner1");
        Thread t2 = new Thread(new Runner2());
        t2.setName("thread-runner2");
        t1.start();
        System.out.println("这是主线程：-----");
        t2.start();
        System.out.println("这是主线程：-----");
    }

    static class Runner1 implements Runnable {

        public void run() {
            for (int i=0; i<10; i++) {
                System.out.println("runner1 run: -------------");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName());
        }
    }

    static class Runner2 implements Runnable {
        public void run() {
            for (int i=0; i<10; i++) {
                System.out.println("runner2 run:-----------------------");
            }
        }
    }

}
