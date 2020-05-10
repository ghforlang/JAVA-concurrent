package com.edu.nbu.cn.utils.countdownlatch;

import lombok.AllArgsConstructor;

import java.util.concurrent.CountDownLatch;

//与ParallelLimit效果一致
public class ParallelLimit2 {

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i=0;i<10;i++){
            Thread t = new Thread(new CountRunnable(cdl));
            t.start();
        }
    }

    @AllArgsConstructor
    static class CountRunnable implements Runnable{
        private CountDownLatch cdl;

        @Override
        public void run() {
            try {
                synchronized (cdl) {
                    cdl.countDown();
                    System.out.println("thread counts : " + cdl.getCount());
                }
                cdl.await();
                System.out.println("concurrency counts : " + (10 - cdl.getCount()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
