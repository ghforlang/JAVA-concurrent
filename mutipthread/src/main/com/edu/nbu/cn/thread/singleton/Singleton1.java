package com.edu.nbu.cn.thread.singleton;

/**
 * 饿汉模式
 */
public class Singleton1 {

    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance(){
        return INSTANCE;
    }


    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();

        //同一个实例
        System.out.println(s1 == s2);
    }
}
