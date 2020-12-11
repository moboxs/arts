import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TwinsLockTest {

    public static void main(String[] args) {

        for (int i=0; i< 10; i++) {
            MyThread t1 = new MyThread();
            Thread t = new Thread(t1);
            t.setName("thread-" + i);
            //t.setDaemon(true);
            t.start();
        }

        for (int i=0; i<10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThread implements Runnable {

        final Lock lock = new TwinsLock();
        final ReentrantLock reentrantLock = new ReentrantLock();
        @Override
        public void run() {
            while (true) {
                reentrantLock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }
}