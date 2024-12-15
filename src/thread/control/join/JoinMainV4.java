package thread.control.join;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class JoinMainV4 {
    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);

        Thread thread1 = new Thread(task1, "t1");

        thread1.start();

        thread1.join(1000);

        log("메인스레드 대기 완료");

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
