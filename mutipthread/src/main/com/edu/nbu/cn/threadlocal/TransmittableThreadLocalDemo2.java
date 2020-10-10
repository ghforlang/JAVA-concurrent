package com.edu.nbu.cn.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 用于常规父子线程
 */
public class TransmittableThreadLocalDemo2 {
    private static ThreadLocal threadLocal = new TransmittableThreadLocal();

    public static void main(String[] args) {
        new Thread(() ->{
            String  mainThreadName = "mainThread_01";
            threadLocal.set(1);

            new Thread(() ->{
                sleep(1L);
                System.out.println(String.format("本地变量改变之前(1), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), threadLocal.get()));
            }).start();

            new Thread(() ->{
                sleep(1L);
                System.out.println(String.format("本地变量改变之前(1), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), threadLocal.get()));
            }).start();

            new Thread(() ->{
                sleep(1L);
                System.out.println(String.format("本地变量改变之前(1), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), threadLocal.get()));
            }).start();
        }).start();


        new Thread(() ->{
            String mainThreadName =  "mainThread_02";
            sleep(1L);
            threadLocal.set(2);
            new Thread(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之后(2), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), threadLocal.get()));
            }).start();

            new Thread(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之后(2), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), threadLocal.get()));
            }).start();

            new Thread(() -> {
                sleep(1L);
                System.out.println(String.format("本地变量改变之后(2), 父线程名称-%s, 子线程名称-%s, 变量值=%s", mainThreadName, Thread.currentThread().getName(), threadLocal.get()));
            }).start();

        }).start();

    }

    private static void sleep(long time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
