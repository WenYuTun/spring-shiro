package com.mycode.springshiro.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author wenyutun
 * @description: 并发场景模拟的工具类
 * @date: 2019/8/10
 * @version: 1.0
 */
public class CountDownLatchUtil {

    private CountDownLatch start;

    private CountDownLatch end;

    private int poolSize;

    public CountDownLatchUtil() {
        this(10);
    }

    public CountDownLatchUtil(int poolSize) {
        this.poolSize = poolSize;
        start = new CountDownLatch(1);
        end = new CountDownLatch(poolSize);
    }

    /**
     * 多线程并发压测接口
     * @param functionalInterface 接口实现
     * @throws InterruptedException
     */
    public void latch(MyFunctionalInterface functionalInterface) throws InterruptedException {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(poolSize,
                new BasicThreadFactory.Builder().namingPattern("test-concurrent-%d").daemon(true).build());
        for (int i = 0; i < poolSize; i++) {
            Runnable run = () -> {
                try {
                    start.await();
                    functionalInterface.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            };
            executorService.submit(run);
        }
        start.countDown();
        end.await();
        executorService.shutdown();
    }

    @FunctionalInterface
    public interface MyFunctionalInterface {
        /**
         * 执行
         */
        void run();
    }
}
