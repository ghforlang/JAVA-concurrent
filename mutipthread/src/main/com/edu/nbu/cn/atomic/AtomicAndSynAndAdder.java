package com.edu.nbu.cn.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicAndSynAndAdder {
    private static  long count = 0L;
    private static AtomicLong count1 = new AtomicLong(0L);
    private static LongAdder count2 = new LongAdder();


    public static void main(String[] args) throws InterruptedException {

        //syn
        Thread[] threads = new Thread[1000];
        Object obj = new Object();
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                synchronized (obj){
                    for(int j = 0;j < 100000;j++){
                        count ++;
                    }
                }
            });
        }
        long startTime = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        System.out.println("synchronized ， " + count +", 耗时:" + (System.currentTimeMillis() - startTime));

        //AtomicLong
        threads = new Thread[1000];
        for(int i=0;i< threads.length;i++){
            threads[i] = new Thread(() ->{
                for(int j=0;j<100000;j++) count1.incrementAndGet();
            });
        }
        startTime = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        System.out.println("atomicLong ，" + count1.get() + ",耗时:" + (System.currentTimeMillis() - startTime));

        //longAdder
        threads = new Thread[1000];
        for(int i=0;i< threads.length;i++){
            threads[i] = new Thread(() ->{
                for(int j=0;j<100000;j++) count2.increment();
            });
        }
        startTime = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        System.out.println("longAdder," + count2.longValue() + " ，耗时:" + (System.currentTimeMillis() - startTime));

    }
}
