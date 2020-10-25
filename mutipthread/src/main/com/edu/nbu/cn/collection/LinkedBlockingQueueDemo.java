package com.edu.nbu.cn.collection;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

   static  BlockingQueue<String> strs = new LinkedBlockingQueue();

    public static void main(String[] args) {

        new Thread(() ->{
            for(int i=0;i<100;i++){
                try {
                    strs.put("a" + i);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(() ->{
            for(;;){
                try {
                    System.out.println(Thread.currentThread().getName() + strs.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }
}
