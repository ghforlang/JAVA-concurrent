package com.edu.nbu.cn.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    //互斥锁
    static Lock lock = new ReentrantLock();
    static int value;

    //默认非公平，读写锁 = 读与读 共享，写与X 互斥
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁
    static Lock readLock = readWriteLock.readLock();
    //写锁
    static Lock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
//        Runnable readR = () -> read(lock);
        Runnable readR = () -> read(readLock);

//        Runnable writeR = () -> write(lock,20);
        Runnable writeR = () -> write(writeLock,20);
        for (int i = 0; i <10 ; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i <2 ; i++) {
            new Thread(writeR).start();
        }
    }

    public static void read(Lock lock){
        try {
            lock.lock();
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("read over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock,int v){
        try {
            lock.lock();
            TimeUnit.MILLISECONDS.sleep(1000);
            value = v;
            System.out.println("write over!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
