package thread.control.join;

import static java.lang.Thread.sleep;
import static util.MyLogger.log;

public class JoinMainV3 {
    public static void main(String[] args) throws InterruptedException {
        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "t1");
        Thread thread2 = new Thread(task2, "t2");

        thread1.start();
        thread2.start();

        //스레드1이 종료되길 기다린 후에 스레드2가 종료되길 기다린다.
        thread1.join();
        thread2.join();

        log("메인스레드 대기 완료");

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
