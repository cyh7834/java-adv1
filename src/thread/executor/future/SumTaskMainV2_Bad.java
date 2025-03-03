package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class SumTaskMainV2_Bad {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SumTask sumTask1 = new SumTask(1, 50);
        SumTask sumTask2 = new SumTask(51, 100);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> submit1 = executorService.submit(sumTask1);
        Integer r1 = submit1.get();

        Future<Integer> submit2 = executorService.submit(sumTask2);
        Integer r2 = submit2.get();

        System.out.println("r1 = " + r1);
        System.out.println("r2 = " + r2);

        int sumAll = r1 + r2;

        System.out.println("sumAll = " + sumAll);
    }

    static class SumTask implements Callable<Integer> {
        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws Exception {
            log("작업 시작");
            sleep(2000);
            int sum = 0;

            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }

            log("작업 완료 result =" + sum);

            return sum;
        }
    }
}
