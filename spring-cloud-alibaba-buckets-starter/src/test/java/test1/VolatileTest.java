package test1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    public static volatile int race = 0;
//    public static AtomicInteger atomicInteger = new AtomicInteger();
//    public static CountDownLatch countDownLatch = new CountDownLatch();
    public static void increase() {
        race++;
    }
    private static final int THREADS_COUNT = 20;
    public static void main(String[] args) {
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    increase();
                }
            }).start();
        }
// 等待所有累加线程都结束
//        while (Thread.activeCount() > 1)
//            Thread.yield();
        System.out.println(race);
    }
}
