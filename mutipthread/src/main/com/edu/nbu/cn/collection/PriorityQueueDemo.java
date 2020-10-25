package com.edu.nbu.cn.collection;

import java.util.PriorityQueue;

/**
 * 优先级队列，基于优先级堆实现,二叉树
 */
public class PriorityQueueDemo {

    static PriorityQueue<String> priorityQueue = new PriorityQueue();

    public static void main(String[] args) {
        priorityQueue.add("z");
        priorityQueue.add("a");
        priorityQueue.add("d");
        priorityQueue.add("v");
        priorityQueue.add("b");
        priorityQueue.add("c");

        System.out.println(priorityQueue);

        for(int i=0;i<6;i++){
            System.out.println(priorityQueue.poll());
        }
    }
}
