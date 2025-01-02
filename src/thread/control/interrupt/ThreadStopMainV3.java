package thread.control.interrupt;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV3 {
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
            while (!Thread.currentThread().isInterrupted()) { // 인터턻트 상태로 변경하지 않음
                log("작업 중");
            }

            // 예외가 발생한 상황이 아니면 인터럽트 상태가 변경되지 않음. 이런 상황에선 다시 정상(false) 상태로 돌려줘야 한다.
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());

            log("자원 정리");
            log("자원 종료");
        }
    }
}
