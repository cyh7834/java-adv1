package thread.control.join;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class JoinMainV2 {
    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "t1");
        Thread thread2 = new Thread(task2, "t2");

        thread1.start();
        thread2.start();

        log("main 스레드 sleep");
        sleep(3000);
        log("main 깨어남");

        log("task1 result = " + task1.result);
        log("task2 result = " + task2.result);

        int sumAll = task1.result + task2.result;
        log("결과 = " + sumAll);
        log("end");
    }

    static class SumTask implements Runnable {
        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sum = 0;

            for (int i = startValue; i <= endValue ; i++) {
                sum += i;
            }

            result = sum;

            log("작업 완료 = " + result);
        }
    }
}
