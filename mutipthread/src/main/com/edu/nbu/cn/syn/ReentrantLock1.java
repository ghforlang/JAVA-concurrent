package com.edu.nbu.cn.syn;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1 extends Thread{

    /**
     * 默认非公平锁
     */
    private static final ReentrantLock lock = new ReentrantLock(true);


    @Override
    public void run() {
       for(int i=0;i<10;i++){
           lock.lock();
           try{
               System.out.println(Thread.currentThread().getName() + "获得锁!");
           }finally {
               lock.unlock();
           }
       }
    }

    public static void main(String[] args) {
        ReentrantLock1 rl = new ReentrantLock1();
        new Thread(rl,"t1").start();
        new Thread(rl,"t2").start();
    }
}
