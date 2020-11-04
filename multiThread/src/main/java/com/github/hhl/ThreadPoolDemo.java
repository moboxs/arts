package com.github.hhl;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        /*
         * 阻塞队列
         */
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10, true);
        ThreadPoolExecutor defaultExecutor = new ThreadPoolExecutor(
                2,
                5,
                60L,
                TimeUnit.SECONDS,
                workQueue,
                new MyThreadFactory(),
                new MyRejectHandler()
        );

    }


    public static class MyThreadFactory implements ThreadFactory {

        public Thread newThread(Runnable r) {
            return null;
        }
    }

    /**
     * 自定义拒绝策略
     */
    public static class MyRejectHandler implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }

}
