public class SyncCounter {
    private int sum = 0;
    public synchronized int incrAndGet() {
        return ++sum;
    }

    public int addAndGet() {
        synchronized (this) {
            return ++sum;
        }
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) {
        int loop = 100000;
        SyncCounter counter = new SyncCounter();
        while (loop-- > 0) {
            counter.incrAndGet();
        }
    }
}
