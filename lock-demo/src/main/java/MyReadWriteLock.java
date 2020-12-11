import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwlock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwlock.writeLock();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(10);

        try {
            writeLock.lock();
            readLock.lock();
        } finally {
            writeLock.unlock();
            readLock.unlock();
        }

    }
}
