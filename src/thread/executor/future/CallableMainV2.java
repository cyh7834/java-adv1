package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class CallableMainV2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        log("submit 호출");
        Future<Integer> submit = executorService.submit(new MyCallable());
        log("future 즉시 반환, future = " + submit);

        log("submit.get() [블로킹] 메서드 호출 시작 -> main 스레드 waiting");
        Integer result = submit.get();
        log("submit.get() [블로킹] 메서드 호출 완료 -> main 스레드 runnable");

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
