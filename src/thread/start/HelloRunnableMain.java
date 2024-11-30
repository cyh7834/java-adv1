package thread.start;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        // runnable 인터페이스를 구현하여 사용하는 방식. (상속에 자유로움)
        // 자바는 다중 상속이 불가능하기 때문에 Thread 클래스 상속 방식은 단점임.
        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
