package thread.cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

public class MyAtomicInteger implements IncrementInteger{
    AtomicInteger atomicInteger = new AtomicInteger(0); // 멀티 스레드를 위한 코드들이 구현되어 있음.

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }
}
