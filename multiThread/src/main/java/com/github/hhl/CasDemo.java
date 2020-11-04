package com.github.hhl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CasDemo {
    private AtomicInteger ati = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final CasDemo cas = new CasDemo();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j =0; j<100; j++) {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    for (int i=0; i< 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }

        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("cas.i = " + cas.i);
        System.out.println("cas.ati = " + cas.ati.get());
        System.out.println("cas cost =" + (System.currentTimeMillis() - start));

    }

    private void count() {
        i++;
    }

    private void safeCount() {
        for (;;) {
            int i = ati.get();
            boolean succ = ati.compareAndSet(i, ++i);
            if (succ) {
                break;
            }
        }
    }
}
