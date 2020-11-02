package com.edu.nbu.cn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPoolDemo {

    public static void main(String[] args) {

        ExecutorService service = Executors.newWorkStealingPool();

        System.out.println(Runtime.getRuntime().availableProcessors());


    }

    static class R implements Runnable{
        @Override
        public void run() {

        }
    }
}
