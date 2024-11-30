package thread.start;

public class HelloBadThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        helloThread.run(); // run() 으로 호출하면 별도의 스레드가 아닌 Main 스레드가 처리하게 되므로 호출하면 안된다.

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
