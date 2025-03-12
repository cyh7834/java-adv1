package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new RunnableTask("taskA"));
        executorService.execute(new RunnableTask("taskB"));
        executorService.execute(new RunnableTask("taskC"));
        executorService.execute(new RunnableTask("longTask", 100_000));

        log("== shutdown 시작 ==");
        shutdownAndAwaitTermination(executorService);
        log("== shutdown 완료 ==");
    }

    private static void shutdownAndAwaitTermination(ExecutorService executorService) {
        executorService.shutdown(); // non-blocking 새로운 작업을 받지 않는다. 처리 중이거나, 큐에 이미 대기중인 작업은 처리. 이후에 풀의 스레드를 종료
        try {
            // 이미 대기중인 작업들을 모두 완료할 때 까지 10초 기다린다.
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                // 정상 종료가 너무 오래걸리면..
                log("서비스 정상 종료 실페 -> 강제 종료 시도");
                executorService.shutdownNow();
                // 작업이 취소될 때 까지 대기한다.
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination() 으로 대기중인 현재 스레드가 인터럽트 될 수 있다.
            executorService.shutdownNow();
        }
    }
}
