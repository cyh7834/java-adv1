package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class PoolSizeMainV3 {
    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS
                , new SynchronousQueue<>());

        log("pool 생성");
        printState(executorService);

        for (int i = 0; i <= 4; i++) {
            String taskName = "task" + i;
            executorService.execute(new RunnableTask(taskName));
            printState(executorService, taskName);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(executorService);

        sleep(3000);
        log("== 대기 시간 초과 ==");
        printState(executorService);

        executorService.close();
        log("완료");
        
    }
}
