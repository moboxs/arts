package com.arts.threads;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//等待通知线程
public class WaitNotifyThread {
    //是否执行的标志
    private static volatile boolean flag = true;
    //对象的监视器锁
    private static Object lock = new Object();

    private static DateFormat format = new SimpleDateFormat("HH:mm:ss");



    private static class WaitThread implements Runnable {
        @Override
        public void run() {
            //加锁，持有对象的监视器锁
            synchronized(lock){
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "  "+ format.format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+" flag is false, running at here!");
            }
        }
    }

    private static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock) {

                System.out.println(Thread.currentThread().getName() + " Hold lock, Notify waitThread at " + format.format(new Date()));
                //获取lock锁，然后执行通知，通知的时候不会释放lock锁
                //只有当前线程退出了lock后，waitThread才有可能从wait返回
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "hold lock again, sleep 5s " + format.format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "hold lock again2, sleep 5s " + format.format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "hold lock again3, sleep 5s " + format.format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new WaitThread(), "waitThread");
        waitThread.start();
        Thread.sleep(1000);
        Thread notifyThread = new Thread(new NotifyThread(), "notifyThread");
        notifyThread.start();
    }
}
