package com.bilibili.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author hanzhuofan
 * @date 2020/6/18 23:16
 */
public class DbeforeAbc {
    private static void runDbeforeAbc() {
        int worker = 1;
        CountDownLatch countDownLatch = new CountDownLatch(worker);
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            DafterAbc.pool.execute(() -> {
                System.out.println(tN + " is working");
                try {
                    Thread.sleep(100);
                    countDownLatch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(tN + " finished");
            });
        }
        DafterAbc.pool.execute(() -> {
            System.out.println("D is waiting for other three threads");
            countDownLatch.countDown();
            System.out.println("All done, D starts working");
        });
    }

    public static void main(String[] args) {
        runDbeforeAbc();
    }
}
