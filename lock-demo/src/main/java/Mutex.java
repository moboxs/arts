import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {

    static class Sync extends AbstractQueuedSynchronizer {

        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //当状态为0时获取锁
        @Override
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

    }
}
