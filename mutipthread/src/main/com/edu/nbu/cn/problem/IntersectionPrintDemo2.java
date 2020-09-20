package com.edu.nbu.cn.problem;

import java.util.concurrent.locks.LockSupport;

public class IntersectionPrintDemo2 {

    private static String s1 = "ABCDEFG";
    private static String s2 = "1234567";

    public static void main(String[] args) {

        Object lock = new Object();
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();

        new Thread(() ->{
            for (int i = 0; i < s1Char.length; i++) {
                synchronized (lock){
                    System.out.print(s1Char[i]);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        },"t1").start();

        new Thread(() -> {
            for (int i = 0; i < s2Char.length ; i++) {
                synchronized (lock){
                    System.out.print(s2Char[i]);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //必须，否则程序无法终止
                    lock.notify();
                }
            }
        },"t2").start();

    }
}
