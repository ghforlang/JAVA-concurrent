package com.edu.nbu.cn.thread.syn;

/**
 * synchronized 与非synchronized方法同时执行
 */
public class Demo1 {

    public synchronized void  m1(){
        System.out.println(Thread.currentThread().getName()  + " m1 starting!");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end!");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName() + " m2 starting!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end!");
    }

    public static void main(String[] args) {
        Demo1 d = new Demo1();
//        new Thread(() -> d.m1(),"t1").start();
//        new Thread(() -> d.m2(),"t2").start();
//
        new Thread(d::m1,"t1").start();
        new Thread(d::m2,"t2").start();
    }
}
