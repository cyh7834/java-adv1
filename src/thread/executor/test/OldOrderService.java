package thread.executor.test;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;
import static util.MyLogger.sleep;

public class OldOrderService {
    public void order(String orderNo) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        List<Callable<Boolean>> tasks = List.of(inventoryWork, shippingWork, accountingWork);

        executorService.invokeAll(tasks);

        log("모든 주문 처리가 성공적으로 완료되었습니다.");

        executorService.close();
    }
    static class InventoryWork implements Callable<Boolean> {
        private final String orderNo;
        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("재고 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }
    static class ShippingWork implements Callable<Boolean>  {
        private final String orderNo;
        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("배송 시스템 알림: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean>  {
        private final String orderNo;
        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {
            log("회계 시스템 업데이트: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}