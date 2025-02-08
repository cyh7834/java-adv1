package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

//BlockingQueue - 기능 확인
public class BoundedQueueV6_2 implements BoundedQueue{
    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean offer = queue.offer(data);
        log("저장 시도 결과 : " + offer);
    }

    @Override
    public String take() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
