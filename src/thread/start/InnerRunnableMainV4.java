package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV4 {
    public static void main(String[] args) {
        log("main() start");

        // 람다식 사용
        Thread thread = new Thread(() -> log("run ()"));

        thread.start();

        log("main() end");
    }
}
