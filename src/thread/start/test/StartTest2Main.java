package thread.start.test;

import static util.MyLogger.log;

public class StartTest2Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        log(i + 1);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.setName("counter");
        thread.start();
    }
}
