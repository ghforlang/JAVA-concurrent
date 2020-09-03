package com.edu.nbu.cn.syn;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo extends  Thread{

    private static final ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
//        for(int i=0;i<10;i++){
            try{
               lock.lock();
               System.out.println(Thread.currentThread().getName() + "获得锁!");
            }finally {
                lock.unlock();
            }
//       }
    }

    public static void main(String[] args) {
        ReentrantLockDemo rl = new ReentrantLockDemo();
        new Thread(rl,"t1").start();
        new Thread(rl,"t2").start();
    }
}
