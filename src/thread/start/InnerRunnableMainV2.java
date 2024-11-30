package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {
    public static void main(String[] args) {
        log("main() start");

        // 익명 클래스 구현
        // 특정 클래스 안에서 간단하게 사용하고 싶을 때 사용
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log("run ()");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
    }
}
