package thread.volatile1;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread t = new Thread(myTask, "work");
        log("runFlag = " + myTask.runFlag);
        t.start();

        sleep(1000);
        log("runFlag를 false로 변경");
        // 메인 스레드와 생성된 스레드는 각각의 캐시 메모리를 사용하기 때문에
        // 메인 스레드에서 runFlag를 변경하여도 다른 스레드에서는 바로 참조할 수 없음.
        myTask.runFlag = false;
        log("runFlag = " + myTask.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {
        //boolean runFlag = true;
        // 캐시 메모리를 참조하지 않고 바로 메인 메모리에 데이터를 읽기/쓰기 함.
        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("task 시작");

            while (runFlag) {

            }

            log("task 종료");
        }

    }
}
