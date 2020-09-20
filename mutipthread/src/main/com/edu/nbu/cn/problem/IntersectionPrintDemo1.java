package com.edu.nbu.cn.problem;

import java.util.concurrent.locks.LockSupport;

public class IntersectionPrintDemo1 {

    private static String s1 = "ABCDEFG";
    private static String s2 = "1234567";
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        char[] s1Char = s1.toCharArray();
        char[] s2Char = s2.toCharArray();

        t1 = new Thread(() ->{
            for (int i = 0; i < s1Char.length; i++) {
                System.out.print(s1Char[i]);
                LockSupport.park();
                LockSupport.unpark(t2);
            }
        },"t1");

        t2 = new Thread(() -> {
            for (int i = 0; i < s2Char.length ; i++) {
                System.out.print(s2Char[i]);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();
    }
}
