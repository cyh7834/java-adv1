package thread.control.join;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class JoinMainV1 {
    public static void main(String[] args) {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "t1");
        Thread thread2 = new Thread(task2, "t2");

        thread1.start();
        thread2.start();

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
