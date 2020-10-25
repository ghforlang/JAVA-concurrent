package com.edu.nbu.cn.collection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue();

        for(int i=0;i<10;i++){
            queue.offer("a " + i);
        }

        System.out.println(queue);
        System.out.println(queue.size());

        //取值不删除,为空抛异常
        System.out.println(queue.element());
        System.out.println(queue.size());

        //取值删除，为空抛异常
        System.out.println(queue.remove());
        System.out.println(queue.size());

        //取值并删除，为空则返回null
        System.out.println(queue.poll());
        System.out.println(queue.size());

        //取值不删除，为空则返回null
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }

}
