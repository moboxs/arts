package com.arts.threads;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ThreadLocalDemo {

    //ThreadLocal<Connection> holder = new ThreadLocal<Connection>();

/*
    private void set(T value){
        Thread t = Thread.currentThread();

        //ThreadLocalMap threadLocalMap = getMap(t);
    }
*/

    private static final ThreadLocal<Person> personThreadLocal = new ThreadLocal<Person>();
    private static final Random random = new Random();
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");

    static class Worker implements Runnable {

        @Override
        public void run() {
            Person p = getPerson();
            int age = random.nextInt(100);
            p.setAge(age);
            System.out.println(Thread.currentThread().getName()+" set age " + age +" at " + format.format(new Date()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" get age "+p.getAge() +" at " + format.format(new Date()));

        }

        protected Person getPerson() {
            Person p = personThreadLocal.get();
            if (p == null) {
                p = new Person();
                personThreadLocal.set(p);
            }

            return p;
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(), "thread111");
        t1.start();
        Thread t2 = new Thread(new Worker(), "thread222");
        t2.start();
        Thread t3 = new Thread(new Worker(), "thread333");
        t3.start();
        Thread t4 = new Thread(new Worker(), "thread444");
        t4.start();
        Thread t5 = new Thread(new Worker(), "thread555");
        t5.start();
        Thread t6 = new Thread(new Worker(), "thread666");
        t6.start();
        Thread t7 = new Thread(new Worker(), "thread777");
        t7.start();
        Thread t8 = new Thread(new Worker(), "thread888");
        t8.start();
    }


}
