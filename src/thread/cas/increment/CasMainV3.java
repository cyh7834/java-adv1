package thread.cas.increment;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class CasMainV3 {
    private static final int THREAD_COUNT = 2;
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("atomicInteger = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println("result = " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {
        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get();
            sleep(100);
            log("getValue : " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1); // 위에서 읽은 값이 유지가 된 상태라면 1을 증가시킴.
            log("result : " + result);

        } while (!result);

        return getValue + 1;
    }
}
