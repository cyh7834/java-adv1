package thread.sync;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class BankMain {
    public static void main(String[] args) throws InterruptedException {
        BankAccountV1 bankAccountV1 = new BankAccountV1(1000);

        Thread t1 = new Thread(new WithdrawTask(bankAccountV1, 800), "t1");
        Thread t2 = new Thread(new WithdrawTask(bankAccountV1, 800), "t2");

        t1.start();
        t2.start();

        sleep(500);
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());
        t1.join();
        t2.join();

        log("최종 잔액: " + bankAccountV1.getBalance());

    }
}
