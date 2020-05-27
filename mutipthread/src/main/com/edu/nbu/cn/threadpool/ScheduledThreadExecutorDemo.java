package com.edu.nbu.cn.threadpool;

import com.edu.nbu.cn.thread.MyThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadExecutorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(new MyThread("fixedRate"),3L, 15L,TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(new MyThread("fixedDelay"),3,15L,TimeUnit.SECONDS);
    }
}
