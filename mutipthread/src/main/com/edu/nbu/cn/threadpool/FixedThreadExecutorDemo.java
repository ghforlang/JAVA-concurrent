package com.edu.nbu.cn.threadpool;

import com.edu.nbu.cn.thread.MyThread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<MyThread> threadList = MyThreadGenerator.generateThreads(5);
        executor.execute(threadList.get(0));
        executor.execute(threadList.get(1));
        executor.execute(threadList.get(2));
        executor.execute(threadList.get(3));
        executor.execute(threadList.get(4));

        executor.shutdown();
    }
}
