package com.edu.nbu.cn.collection;

import java.util.concurrent.SynchronousQueue;

/**
 * 用于线程间直接交换数据，比Exchanger使用方便
 */
public class SynchronousQueueDemo {

    static SynchronousQueue<String> queue = new SynchronousQueue();


    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        queue.put("a");
        System.out.println(queue.size());
    }

}
