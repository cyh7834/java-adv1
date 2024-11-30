package thread.start.test;

import static util.MyLogger.log;

public class StartTest3Main {
    public static void main(String[] args) {
        PrintWork printA = new PrintWork("A", 1000);
        PrintWork printB = new PrintWork("B", 500);

        Thread threadA = new Thread(printA, "Thread-A");
        Thread threadB = new Thread(printB, "Thread-B");

        threadA.start();
        threadB.start();
    }

    static class PrintWork implements Runnable {
        private String content;
        private int sleepMs;

        public PrintWork(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    log(content);
                    Thread.sleep(sleepMs);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
