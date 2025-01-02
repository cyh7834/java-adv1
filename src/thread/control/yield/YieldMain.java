package thread.control.yield;

public class YieldMain {
    static final int THREAD_COUNT = 1000;
    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);
                //sleep(1); //하나의 스레드가 순차적으로 실행되지 않고 다른 스레드의 실행으로 바뀜
                //Thread.yield(); //스레드의 상태를 runnable로 유지하되 스케쥴링 대기 큐로 들어감(runnable에서 바뀌지 않음). 다른 스레드를 실행함.
            }
        }
    }
}
