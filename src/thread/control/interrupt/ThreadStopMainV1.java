package thread.control.interrupt;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class ThreadStopMainV1 {
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(4000);
        log("작업 중단 지시");
        myTask.runFlag = false;
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업 중");
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
