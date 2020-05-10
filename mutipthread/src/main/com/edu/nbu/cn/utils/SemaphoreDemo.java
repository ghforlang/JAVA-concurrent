package com.edu.nbu.cn.utils;

import java.text.SimpleDateFormat;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    //Semaphore synchronized 的加强版，计数信号量，必须由获取它的线程释放；常用于限制可访问资源的线程数量，如限流；
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
    //同一时刻只允许两个线程同时运行，默认非公平
    private static Semaphore semaphore = new Semaphore(2);
    //先启动线程先获得通路即为公平,非100%保证
    private static Semaphore semaphore2 = new Semaphore(2,true);

    public static void main(String[] args) {
        for(int i=0 ; i<10 ; i ++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //防止中断，可采用acquireUninterruptibly、acquireUninterruptibly两个方法，分别代替acquire、release方法
                        semaphore.acquire();
                        System.out.println("当前可用通路数 "+ semaphore.availablePermits());
                        System.out.println("当前等待线程数 " + semaphore.getQueueLength());
                        System.out.println("是否有线程在等待通路 " + semaphore.hasQueuedThreads());
                        System.out.println(Thread.currentThread().getName() + " start at " + sdf.format(System.currentTimeMillis()));
//                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " end at " + sdf.format(System.currentTimeMillis()));
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"thread-" + i);
            thread.start();
        }
    }
}
