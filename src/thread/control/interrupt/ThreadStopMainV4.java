package thread.control.interrupt;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV4 {
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시");
        thread.interrupt(); // 인터럽트 상태가 true.
        log("work 스레드 인터럽트 상태1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (!Thread.interrupted()) { // 인터턻트 상태를 변경시킴
                log("작업 중");
            }

            // interrupted()를 통해 false로 바뀜.
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            log("자원 정리");
            log("자원 종료");
        }
    }
}
