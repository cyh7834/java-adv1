package thread.executor.future;

import java.util.concurrent.*;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class FutureCancelMain {
    private static boolean mayInterruptIfRunning = true;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> submit = executorService.submit(new MyTask());
        log("Future.state : " + submit.state());

        sleep(3000);

        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancel = submit.cancel(mayInterruptIfRunning);
        log("cancel(" + mayInterruptIfRunning + ") result " + cancel);

        try {
            log("Future result: " + submit.get());
        } catch (CancellationException e) {
            log("Future는 이미 취소되었습니다.");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) {
                    log("작업 중 : " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                log("인터럽트 발생");
                return "interrupted";
            }

            return "success";
        }
    }
}
