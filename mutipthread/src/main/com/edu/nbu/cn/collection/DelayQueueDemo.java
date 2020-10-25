package com.edu.nbu.cn.collection;

import java.util.concurrent.DelayQueue;

/**
 * 常用语延迟队列实现,task须实现Delayed接口
 */
public class DelayQueueDemo {
    static DelayQueue<Task> deleayQueue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        deleayQueue.put(new Task("t1",now + 2000));
        deleayQueue.put(new Task("t2",now + 500));
        deleayQueue.put(new Task("t3",now + 1000));
        deleayQueue.put(new Task("t4",now + 3000));
        deleayQueue.put(new Task("t5",now + 100));

        System.out.println(deleayQueue);

        for(int i=0;i<5;i++){
            System.out.println(deleayQueue.take());
        }
    }
}
