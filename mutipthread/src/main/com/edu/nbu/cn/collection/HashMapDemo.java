package com.edu.nbu.cn.collection;

import java.util.HashMap;
import java.util.UUID;

/**
 * 无锁，非线程安全
 */
public class HashMapDemo {
    static HashMap<UUID,UUID> hashMap = new HashMap<>();

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
        System.out.println(hashMap.size());

//        long start1 = System.currentTimeMillis();
//        for(int i = 0 ;i< 100;i++){
//            new Thread(() ->{
//                for(int j=0;j<1000;j ++)
//                    hashMap.get(keys[10]);
//            }).start();
//        }
//        System.out.println(System.currentTimeMillis() - start1);
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
                hashMap.put(keys[i],values[i]);
            }
        }
    }
}
