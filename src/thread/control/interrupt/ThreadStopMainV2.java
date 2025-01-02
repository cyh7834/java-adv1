package thread.control.interrupt;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV2 {
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시");
        thread.interrupt(); // 인터럽트 상태가 true.
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            try {
                while (true) {
                    log("작업 중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                // interrupt가 호출되면 예외가 발생하고 잠들어있던 스레드가 깨어남.
                log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message=" + e.getMessage());
                log("State=" + Thread.currentThread().getState()); //runnable
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
