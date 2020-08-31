package com.edu.nbu.cn.thread.syn;

/***
 * 可重入性;若不可重入，子类调用父类方法会出现死锁
 */
public class Demo3 {

    static class T extends Demo3{
        synchronized void m3(){
            System.out.println("m3 start!");
            super.m1();
            System.out.println("m3 end!");
        }
    }

    synchronized void m1(){
        System.out.println("m1 start!");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //可以调用m2
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        System.out.println("m2 start!");
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end!");
    }

    public static void main(String[] args) {

        //可进入不同的synchronized方法
//        Demo3 d = new Demo3();
//        d.m1();

        //可访问父类sychronized方法
        new T().m3();
    }

}
