package com.bilibili.thread;

import java.util.concurrent.*;

/**
 * @author hanzhuofan
 * @date 2020/6/18 21:00
 */
public class DafterAbc {
    static ExecutorService pool = new ThreadPoolExecutor(10, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10));

    private static void runDafterAbc() {
        int worker = 3;
        CountDownLatch countDownLatch = new CountDownLatch(worker);
        pool.execute(() -> {
            System.out.println("D is waiting for other three threads");
            try {
                countDownLatch.await();
                System.out.println("All done, D starts working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            pool.execute(() -> {
                System.out.println(tN + " is working");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(tN + " finished");
                countDownLatch.countDown();
            });
        }
    }

    public static void main(String[] args) {
        runDafterAbc();
    }
}
