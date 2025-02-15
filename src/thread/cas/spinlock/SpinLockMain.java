package thread.cas.spinlock;

import static util.MyLogger.log;

public class SpinLockMain {
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();

                try {
                    // critical section
                    log("비즈니스 로직 실행");
                } finally {
                    spinLock.unlock();
                }

            }
        };

        Thread t1 = new Thread(runnable, "T1");
        Thread t2 = new Thread(runnable, "T2");

        t1.start();
        t2.start();
    }
}
