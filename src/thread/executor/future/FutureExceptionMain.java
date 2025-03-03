package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class FutureExceptionMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        log("작업 전달");
        Future<Integer> submit = executorService.submit(new ExCallable());
        sleep(1000);

        try {
            log("future.get() 호출 시도, future.state() : " + submit.state());
            Integer i = submit.get();
            log("result : " + i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log("e = " + e);
            Throwable cause = e.getCause();
            log("cause = " + cause);
        }

        executorService.close();
    }

    static class ExCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log("예외 발생");

            throw new IllegalStateException("ex");
        }
    }
}
