package com.edu.nbu.cn.threadpool;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    private static final ThreadFactory tf = Executors.defaultThreadFactory();

    public static void main(String[] args) {

        //推荐使用自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,//核心数
                5, //最大线程数
                500L, //空闲线程最大空闲时间
                TimeUnit.MILLISECONDS, //空闲时间单位
                new ArrayBlockingQueue<Runnable>(3), //阻塞队列
                Executors.defaultThreadFactory(), //线程池，负责生成线程
                new ThreadPoolExecutor.AbortPolicy()); // 拒绝处理器，JDK 内置4种，但是可以自己实现
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("TASK RUNNING!");
        }
    }
}
