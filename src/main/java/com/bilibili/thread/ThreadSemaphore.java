package com.bilibili.thread;

import java.util.concurrent.Semaphore;

/**
 * @author hanzhuofan
 * @date 2020/6/21 16:07
 */
public class ThreadSemaphore {
    static int result = 0;
    public static void main(String[] args) {
        int threadCount = 3;
        Thread[] threads = new Thread[threadCount];
        final Semaphore[] syncObjects = new Semaphore[threadCount];
        for (int i = 0; i < threadCount; i++) {
            syncObjects[i] = new Semaphore(1);
            if (i != threadCount-1){
                try {
                    syncObjects[i].acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < threadCount; i++) {
            final Semaphore lastSemphore = i == 0 ? syncObjects[threadCount - 1] : syncObjects[i - 1];
            final Semaphore curSemphore = syncObjects[i];
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            lastSemphore.acquire();
                            System.out.println("thread" + index + ": " + result++);
                            if (result > 100){
                                System.exit(0);
                            }
                            curSemphore.release();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }
}
