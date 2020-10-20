package com.edu.nbu.cn.collection;

import java.util.Hashtable;
import java.util.UUID;

/**
 * 自带锁，基本不用，性能极差，同步方式采用synchronized
 */
public class HashTableDemo {

    static Hashtable<UUID,UUID> hashtable = new Hashtable<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static int THREAD_COUNT = Constants.THREAD_COUNT;

    static{
        for(int i=0;i<count ; i++){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[100];
        for(int i=0;i<100;i++){
            threads[i] = new MyThread(i * 10000);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for(Thread thread : threads){
            thread.join();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(hashtable.size());

        long start1 = System.currentTimeMillis();
        for(int i = 0 ;i< 100;i++){
            new Thread(() ->{
                for(int j=0;j<1000;j ++)
                hashtable.get(keys[10]);
            }).start();
        }
        System.out.println(System.currentTimeMillis() - start1);
    }

    static class MyThread extends Thread{
        private int start;
        private int gap = count / THREAD_COUNT;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for(int i=start;i<gap + start;i++){
                hashtable.put(keys[i],values[i]);
            }
        }
    }
}
