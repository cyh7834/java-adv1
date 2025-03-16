package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        log("pool 생성");
        printState(executorService);

        for (int i = 0; i <= 6; i++) {
            String taskName = "task" + i;
            executorService.execute(new RunnableTask(taskName));
            printState(executorService, taskName);
        }

        executorService.close();
        log("완료");
        
    }
}
