package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class CallableMainV1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> submit = executorService.submit(new MyCallable());
        Integer result = submit.get();
        System.out.println("result = " + result);
        executorService.close();
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("callable 시작");
            sleep(2000);
            int i = new Random().nextInt(10);
            log("callable 완료");

            return i;
        }
    }
}
