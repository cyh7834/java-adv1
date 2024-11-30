package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV3 {
    public static void main(String[] args) {
        log("main() start");

        // 변수 선언 없이 바로 사용
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log("run ()");
            }
        });

        thread.start();

        log("main() end");
    }
}
