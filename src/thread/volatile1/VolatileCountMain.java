package thread.volatile1;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(1000);

        myTask.flag = false;
        log("flag = " + myTask.flag + ", count = " + myTask.count + " in main");
    }

    static class MyTask implements Runnable {
        volatile boolean flag = true;
        volatile long count;
//        boolean flag = true;
//        long count;

        @Override
        public void run() {
            while(flag) {
                count++;

                if ((count % 100_000_000) == 0) {
                    log("flag = " + flag + ", count = " + count);
                }
            }
            log("flag = " + flag + " " + count + ", count = 종료");
        }
    }
}
