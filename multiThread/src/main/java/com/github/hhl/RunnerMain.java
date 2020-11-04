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

}
