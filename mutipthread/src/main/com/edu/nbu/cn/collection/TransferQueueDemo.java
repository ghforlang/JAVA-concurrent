package com.edu.nbu.cn.collection;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 确认消息已经被消费；
 */
public class TransferQueueDemo {
    static LinkedTransferQueue<String> queue = new LinkedTransferQueue();

    public static void main(String[] args) {
        new Thread(() ->{
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        queue.put("a");

        new Thread(() ->{
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
