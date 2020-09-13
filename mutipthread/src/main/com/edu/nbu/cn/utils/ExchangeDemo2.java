package com.edu.nbu.cn.utils;

import java.util.concurrent.Exchanger;

public class ExchangeDemo2 {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() ->{
            String s = "T1";
            try {
              s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1.s = " + s);
        },"t1").start();

        new Thread(() ->{
            String s = "T2";
            try {
               s =  exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2.s = " + s);
        },"t2").start();
    }
}
