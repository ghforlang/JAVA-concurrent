package com.edu.nbu.cn.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class ParallelLimit {
    private static CountDownLatch cdl = new CountDownLatch(10);

    public static void main(String[] args) {
        for(int i= 0 ;i<10 ; i++ ){
            Thread t = new Thread(new CountDownRunnable());
            t.start();
        }
    }

    static class CountDownRunnable implements Runnable{
        @Override
        public void run() {
            try {
                cdl.countDown();
                System.out.println("thread count : " + cdl.getCount());
                cdl.await();
                System.out.println("concurrency count : " + (10 - cdl.getCount()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


