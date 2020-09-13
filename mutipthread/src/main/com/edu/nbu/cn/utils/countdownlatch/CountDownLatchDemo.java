package com.edu.nbu.cn.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;


//典型用法1： 启动服务时，主线程需要等待多个组件加载完毕，再继续执行
//典型用法2： 初始化共享的countDownLatch(1)，，多个线程开始执行任务前首先countDownLatch.await(),主线程调用countDown()时，多个线程同时被唤醒；参考LotsOfThreadInParallel
public class CountDownLatchDemo {



    //AQS子类，通过计数器实现，计数器初始值是线程数量；每当一个线程执行完毕，计数器-1，=0时表示所有线程都执行完毕，闭锁上等待的线程就可以恢复工作了
    private  static CountDownLatch cdl = new CountDownLatch(2);

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "开始执行!");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "执行!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }
        },"t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "开始执行!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
            }
        },"t2");
        //如果t2不启动，主线程会一直等待
//        t2.start();

        System.out.println("等待两个线程执行完毕!");

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程恢复执行！");
    }
}
