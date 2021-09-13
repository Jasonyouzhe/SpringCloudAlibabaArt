package com.hyl.learnerJVM.classtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * class测试类
 *
 * @author hyl
 * @version v1.0: ClassTest.java, v 0.1 2020/8/7 8:03 $
 */
public class ClassTest {

    static {
        i = 0; // 给变量复制可以正常编译通过
    }
    static int i = 1;

    public static final ExecutorService NEW_MEDIA_ON_DUTY =  new ThreadPoolExecutor(2,2,10, TimeUnit.SECONDS, new SynchronousQueue<>(false),
            // 丢弃队列中等待时间最长的任务，并执行当前提交的任务，如果线程池已经关闭，任务将被丢弃。
            new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            NEW_MEDIA_ON_DUTY.submit(()->{
                while (true){

                }
            });
        }
    }
}
