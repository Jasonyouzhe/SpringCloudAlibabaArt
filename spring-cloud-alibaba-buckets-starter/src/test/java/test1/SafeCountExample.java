package test1;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class SafeCountExample {

    //请求总数
    private static int clientTotal = 5000;
    //线程数量
    private static int threadTotal = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws  Exception{
//        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadTotal,true);

        //每次固定数量的线程获取许可
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal ; i++) {
            new Thread(()->{
                try {
//                    semaphore.acquire();
                    add();
//                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
//            exec.execute(()->{
//                try {
//                    semaphore.acquire();
//                    add();
//                    semaphore.release();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                countDownLatch.countDown();
//            });
        }
        countDownLatch.await();
//        exec.shutdown();
        System.out.println(count.get());

    }
    private static void add(){
        count.incrementAndGet();
    }
}
