package com.edu.nbu.cn.threadpool;

import com.edu.nbu.cn.thread.MyThread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        List<MyThread> myThreadList = MyThreadGenerator.generateThreads(5);

        executor.execute(myThreadList.get(0));
        executor.execute(myThreadList.get(1));
        executor.execute(myThreadList.get(2));
        executor.execute(myThreadList.get(3));
        executor.execute(myThreadList.get(4));

        executor.shutdown();

    }
}
