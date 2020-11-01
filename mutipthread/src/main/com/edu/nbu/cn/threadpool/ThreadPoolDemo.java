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
                new ArrayBlockingQueue<Runnable>(4), //阻塞队列
                Executors.defaultThreadFactory(), //线程池，负责生成线程
                new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝处理器，JDK 内置4种，AbortPolicy,DiscardPolicy,DiscardOldestPolicy,CallRunsPolicy但是可以自己实现

        for (int i = 0; i < 8 ; i++) {
            executor.execute(new Task(i));
        }

        System.out.println(executor.getQueue());

        executor.execute(new Task(100));

        System.out.println(executor.getQueue());
        System.out.println("operate over");

        executor.shutdown();

    }

    static class Task implements Runnable{
        private int id;

        public Task(int id){
            this.id = id;
        }

        @Override
        public void run() {
//            System.out.println(Thread.currentThread().getName());
        }

        @Override
        public String toString() {
            return "Task{" +
                    "id=" + id +
                    '}';
        }
    }
}
