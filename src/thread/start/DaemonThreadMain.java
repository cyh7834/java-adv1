package thread.start;

public class DaemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        // JVM은 사용자 스레드의 실행은 기다려주지만, 데몬 스레드의 수행을 기다려주지 않는다.
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }

    static class DaemonThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": run()");

            // 스레드 내의 예외는 밖으로 던질 수 없고 바로 처리해줘야 함.
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": end()");

        }
    }
}
