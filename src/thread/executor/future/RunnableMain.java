package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable, "t1");
        thread.start();
        thread.join();
        int value = myRunnable.value;
        log(value);
    }

    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
}
