package com.edu.nbu.cn.thread.singleton;

/**
 * 静态内部类
 */
public class Singleton4 {

    private static class Singleton{
        private static Singleton4 INSTANCE = new Singleton4();
    }

    public static final Singleton4 getInstance(){
        return Singleton.INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread( () ->{
                System.out.println(getInstance());
            }).start();
        }
    }
}
